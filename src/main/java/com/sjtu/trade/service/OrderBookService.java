package com.sjtu.trade.service;

import com.sjtu.trade.entity.OrderBook;

public interface OrderBookService {
    OrderBook findMarketDepth(Long id);
}
