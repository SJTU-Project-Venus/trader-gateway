package com.sjtu.trade.service;

import com.sjtu.trade.entity.Order;

import java.util.List;

public interface OrderService {
    boolean CreateOrder(Order order);
    List<Order> PendingOrder(String traderName);
    List<Order> HistoryOrder(String traderName);
}
