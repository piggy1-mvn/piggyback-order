package com.incentives.piggyback.order.service;

import com.incentives.piggyback.order.dto.OrderEntity;
import com.incentives.piggyback.order.entity.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {

	OrderEntity createOrder(Order order);

	String deleteOrder(String orderId);

	OrderEntity updateOrder(Order order);

	OrderEntity getOrder(String orderId);

	ResponseEntity<String> getOrderType(HttpServletRequest httpRequest);

	Iterable<OrderEntity> getAllOrder();

}