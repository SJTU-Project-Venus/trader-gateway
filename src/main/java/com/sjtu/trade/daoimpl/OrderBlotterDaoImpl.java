package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.repository.OrderBlotterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderBlotterDaoImpl implements OrderBlotterDao {
    @Autowired
    private OrderBlotterRepository orderBlotterRepository;
    @Override
    public List<OrderBlotter> AllOrderBlotters(){
        return orderBlotterRepository.findAll();
    }
}
