package com.sjtu.trade.dao;

import com.sjtu.trade.entity.Order;

public interface OrderDao {
    boolean Create(Order order);
}
