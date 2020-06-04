package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderBookDao;
import com.sjtu.trade.entity.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderBookDaoImpl implements OrderBookDao {

    // 注 这里的数据主要通过 Websocket中从Broker拿到。
    @Override
    public OrderBook findOrderBook(Long id){
        return new OrderBook();
    }
}
