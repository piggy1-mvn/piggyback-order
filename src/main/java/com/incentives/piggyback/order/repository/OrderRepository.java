package com.incentives.piggyback.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incentives.piggyback.order.dto.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}