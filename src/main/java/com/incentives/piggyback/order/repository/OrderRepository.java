package com.incentives.piggyback.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incentives.piggyback.order.dto.OrderEntity;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    List<OrderEntity> findByPartnerId(String partnerId);
}