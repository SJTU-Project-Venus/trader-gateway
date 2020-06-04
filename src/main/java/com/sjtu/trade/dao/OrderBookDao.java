package com.sjtu.trade.dao;

import com.sjtu.trade.entity.OrderBook;

public interface OrderBookDao {
    OrderBook findOrderBook(Long id);
}
