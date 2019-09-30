package com.incentives.piggyback.order.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.incentives.piggyback.order.entity.Location;

import lombok.Data;

@Data
@Document(collection = "order")
public class OrderEntity {

	@Id
	private String orderId;
	private String partnerId;
	private String orderType;
	private String orderStatus;
	private Integer optimizationDuration;
	private Location orderLocation;
	private Integer maxOptimizations;
	private Integer optimizationRadius;
	private Long initiatorUserId;
	private Date createdDate;
	private Date lastModifiedDate;
	private Integer isActive;
	private String partnerDisplayName;
	private String partnerRedirectUrl;
	private String partnerWebHookAddress;
}