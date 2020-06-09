package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.repository.OrderBlotterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component("orderBlotterDao")
public class OrderBlotterDaoImpl implements OrderBlotterDao {
    @Autowired
    private OrderBlotterRepository orderBlotterRepository;
    @Override
    public List<OrderBlotter> AllOrderBlotters(){
        return orderBlotterRepository.findAll();
    }

    @Override
    public boolean Create(OrderBlotter orderBlotter){
        orderBlotterRepository.save(orderBlotter);
        return true;
    }

    @Override
    public List<OrderBlotter> HistoryUsername(String name){
        return orderBlotterRepository.findByBuyerTraderNameOrSellerTraderName(name,name);
    }
}
