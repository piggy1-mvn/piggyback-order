package com.incentives.piggyback.order.util.constants;

public interface Constant {
    String ORDER_PUBLISHER_TOPIC = "order.events.topic";
    String ORDER_SOURCE_ID = "3";
    String ORDER_CREATED_EVENT = "Order Events Created";
    String ORDER_UPDATED_EVENT = "Order Events Updated";
    String ORDER_DEACTIVATED_EVENT = "Order Events Deactivated";
	Integer SUCCESS_STATUS = 200;
	Integer FAILURE_STATUS = 101;
}