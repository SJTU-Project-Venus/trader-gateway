package com.sjtu.trade.repository;

import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {
    List<Order> findByTraderName(String name);
}
