package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dao.OrderBookDao;
import com.sjtu.trade.entity.OrderBook;
import com.sjtu.trade.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    @Autowired
    private OrderBookDao orderBookDao;
    @Override
    public OrderBook findMarketDepth(Long id){
        return orderBookDao.findOrderBook(id);
    }
}
