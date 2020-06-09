package com.sjtu.trade.dao;

import com.sjtu.trade.entity.OrderBlotter;

import java.util.List;

public interface OrderBlotterDao {
    List<OrderBlotter> AllOrderBlotters();
    List<OrderBlotter> HistoryUsername(String name);
    boolean Create(OrderBlotter orderBlotter);
}
