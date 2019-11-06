package com.incentives.piggyback.order.controller;

import com.incentives.piggyback.order.service.OrderService;
import com.incentives.piggyback.order.util.RestResponse;
import com.incentives.piggyback.order.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incentives.piggyback.order.dto.OrderEntity;
import com.incentives.piggyback.order.entity.Order;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<RestResponse<OrderEntity>> createOrder(@RequestBody Order Order) {
		return RestUtils.successResponse(orderService.createOrder(Order));
	}

	@DeleteMapping
	public ResponseEntity<RestResponse<String>> deleteOrder(
			@RequestParam("orderId") String orderId) {
		return RestUtils.successResponse(orderService.deleteOrder(orderId));
	}

	@PutMapping
	public ResponseEntity<RestResponse<OrderEntity>> updateOrder(
			@RequestBody Order order) {
		return RestUtils.successResponse(orderService.updateOrder(order));
	}

	@GetMapping
	public ResponseEntity<RestResponse<OrderEntity>> getOrder(
			@RequestParam("orderId") String orderId) {
		return RestUtils.successResponse(orderService.getOrder(orderId));
	}

	@GetMapping("/categories")
	public ResponseEntity<ResponseEntity<String>> getOrderType(HttpServletRequest request) {
		return ResponseEntity.ok(orderService.getOrderType(request));
	}

	@GetMapping("/")
	public ResponseEntity<RestResponse<Iterable<OrderEntity>>> getAllOrder() {
		return RestUtils.successResponse(orderService.getAllOrder());
	}

	@GetMapping("/partnerId")
	public ResponseEntity<RestResponse<Iterable<OrderEntity>>> getOrderByPartnerId(@RequestParam("partnerId") String partnerId) {
		return RestUtils.successResponse(orderService.getOrderByPartnerId(partnerId));
	}

}