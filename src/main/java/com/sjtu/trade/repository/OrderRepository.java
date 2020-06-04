package com.sjtu.trade.repository;

import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
