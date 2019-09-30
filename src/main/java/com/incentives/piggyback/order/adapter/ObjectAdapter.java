package com.incentives.piggyback.order.adapter;

import java.util.Calendar;

import com.incentives.piggyback.order.dto.OrderEntity;
import com.incentives.piggyback.order.entity.Order;
import com.incentives.piggyback.order.util.CommonUtility;

public class ObjectAdapter {

	public static OrderEntity getOrderEntity(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderId(order.getOrderId());
		orderEntity.setPartnerId(order.getPartnerId());
		orderEntity.setOrderType(order.getOrderType());
		orderEntity.setOptimizationDuration(order.getOptimizationDuration());
		orderEntity.setMaxOptimizations(order.getMaxOptimizations());
		orderEntity.setOrderLocation(order.getOrderLocation());
		orderEntity.setInitiatorUserId(order.getInitiatorUserId());
		orderEntity.setOrderStatus(order.getOrderStatus());
		orderEntity.setOptimizationRadius(order.getOptimizationRadius());
		orderEntity.setCreatedDate(Calendar.getInstance().getTime());
		orderEntity.setLastModifiedDate(Calendar.getInstance().getTime());
		orderEntity.setPartnerDisplayName(order.getPartnerDisplayName());
		orderEntity.setPartnerRedirectUrl(order.getPartnerRedirectUrl());
		orderEntity.setPartnerWebHookAddress(order.getPartnerWebHookAddress());
		orderEntity.setIsActive(1);
		return orderEntity;
	}

	public static OrderEntity updateOrderEntity(
			OrderEntity currentOrder, Order modifiedOrder) {
		if (CommonUtility.isValidString(modifiedOrder.getPartnerId()))
			currentOrder.setPartnerId(modifiedOrder.getPartnerId());

		if (CommonUtility.isValidString(modifiedOrder.getOrderType()))
			currentOrder.setOrderType(modifiedOrder.getOrderType());

		if (modifiedOrder.getOptimizationDuration() != 0)
			currentOrder.setOptimizationDuration(modifiedOrder.getOptimizationDuration());

		if (modifiedOrder.getMaxOptimizations() >= 0)
			currentOrder.setMaxOptimizations(modifiedOrder.getMaxOptimizations());

		if (!CommonUtility.isNullObject(modifiedOrder.getOrderLocation()))
			currentOrder.setOrderLocation(modifiedOrder.getOrderLocation());

		if (CommonUtility.isValidLong(modifiedOrder.getInitiatorUserId()))
			currentOrder.setInitiatorUserId(modifiedOrder.getInitiatorUserId());

		if (CommonUtility.isValidString(modifiedOrder.getOrderStatus()))
			currentOrder.setOrderStatus(modifiedOrder.getOrderStatus());

		if (modifiedOrder.getOptimizationRadius() != 0)
			currentOrder.setOptimizationRadius(modifiedOrder.getOptimizationRadius());

		currentOrder.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentOrder;
	}
}