package com.incentives.piggyback.order.serviceimpl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;

import com.incentives.piggyback.order.entity.Partner;
import com.incentives.piggyback.order.exception.InvalidRequestException;
import com.incentives.piggyback.order.publisher.OrderEventPublisher;
import com.incentives.piggyback.order.repository.OrderRepository;
import com.incentives.piggyback.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.incentives.piggyback.order.adapter.ObjectAdapter;
import com.incentives.piggyback.order.dto.OrderEntity;
import com.incentives.piggyback.order.dto.PartnerResponse;
import com.incentives.piggyback.order.entity.Order;
import com.incentives.piggyback.order.exception.ExceptionResponseCode;
import com.incentives.piggyback.order.exception.PiggyException;
import com.incentives.piggyback.order.util.CommonUtility;
import com.incentives.piggyback.order.util.constants.Constant;
import com.incentives.piggyback.order.util.constants.Preferences;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderEventPublisher.PubsubOutboundGateway messagingGateway;

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	Gson gson = new Gson();

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public OrderEntity createOrder(Order order) {
		Partner partner = getPartnerById(order.getPartnerId());
		order.setPartnerDisplayName(partner.getPartnerName());
		order.setPartnerWebHookAddress(partner.getPartnerWebHookAddress());
		OrderEntity orderEntity = orderRepository.save(ObjectAdapter.getOrderEntity(order));
		publishOrder(orderEntity, Constant.ORDER_CREATED_EVENT);
		return orderEntity;
	}

	private Partner getPartnerById(String partnerId) {
		String url = env.getProperty("partner.api.get.partner.by.id");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("partnerId", partnerId);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<PartnerResponse> response =
				restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
						entity, PartnerResponse.class);
		if (CommonUtility.isNullObject(response.getBody().getData()))
			throw new InvalidRequestException("Partner not found with ID: " + partnerId);
		return response.getBody().getData();
	}

	@Override
	public OrderEntity getOrder(String orderId) {
		Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);
		if (orderOptional.isPresent()) {
			return orderOptional.get();
		} else {
			throw new PiggyException(ExceptionResponseCode.USER_DATA_NOT_FOUND_IN_RESPONSE);
		}
	}

	public Iterable<OrderEntity> getAllOrder() {
		return orderRepository.findAll();
	}

	@Override
	public ResponseEntity getOrderType() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orderType", Preferences.values());
		return  ResponseEntity.ok(map);
	}

	@Override
	public String deleteOrder(String orderId) {
		OrderEntity order = getOrder(orderId);
		order.setIsActive(0);
		orderRepository.save(order);
		publishOrder(order, Constant.ORDER_DEACTIVATED_EVENT);
		return "Order cancelled!";
	}

	@Override
	public OrderEntity updateOrder(Order order) {
		OrderEntity currentOrderValue = getOrder(order.getOrderId());
		OrderEntity updatedOrderValue = orderRepository.save(ObjectAdapter.updateOrderEntity(currentOrderValue, order));
		publishOrder(updatedOrderValue, Constant.ORDER_UPDATED_EVENT);
		return updatedOrderValue;
	}

	private void publishOrder(OrderEntity order, String status) {
		messagingGateway.sendToPubsub(
				CommonUtility.stringifyEventForPublish(
						gson.toJson(order),
						status,
						Calendar.getInstance().getTime().toString(),
						"",
						Constant.ORDER_SOURCE_ID
				));
	}

}