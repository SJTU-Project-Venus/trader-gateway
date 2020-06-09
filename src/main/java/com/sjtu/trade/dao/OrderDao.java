package com.sjtu.trade.dao;

import com.sjtu.trade.entity.Order;

import java.util.List;

public interface OrderDao {
    boolean Create(Order order);

    List<Order> findByUserPendingOrder(String name);

    List<Order> findByUserDoneOrder(String name);
}
