package com.incentives.piggyback.order.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Partner {
	private String partnerId;
	private String partnerName;
	private String partnerDescription;
	private String partnerWebHookAddress;
	private String partnerOfficeAddress;
	private String partnerMobile;
	private List<String> userIds;
	private Date createdDate;
	private Date lastModifiedDate;
	private Integer isActive;
}