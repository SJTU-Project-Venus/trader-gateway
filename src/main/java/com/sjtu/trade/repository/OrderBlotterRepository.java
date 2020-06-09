package com.sjtu.trade.repository;

import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderBlotter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderBlotterRepository extends MongoRepository<OrderBlotter, String> {
     List<OrderBlotter> findByBuyerTraderNameOrSellerTraderName(String name,String name1);
}
