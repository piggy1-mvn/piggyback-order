package com.incentives.piggyback.order.entity;

import lombok.Data;

@Data
public class Order {

	private String orderId;
	private String partnerId;
	private String orderType;
	private String orderStatus;
	private Integer optimizationDuration;
	private Location orderLocation;
	private Integer maxOptimizations;
	private Integer optimizationRadius;
	private Long initiatorUserId;
	private String partnerDisplayName;
	private String partnerRedirectUrl;
	private String partnerWebHookAddress;
}