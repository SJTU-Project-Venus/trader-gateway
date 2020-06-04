package com.sjtu.trade.repository;

import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderBlotter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderBlotterRepository extends MongoRepository<OrderBlotter, Long> {

}
