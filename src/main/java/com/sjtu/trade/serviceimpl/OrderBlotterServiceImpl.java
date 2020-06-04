package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.service.OrderBlotterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBlotterServiceImpl implements OrderBlotterService {
    @Autowired
    private OrderBlotterDao orderBlotterDao;
    @Override
    public List<OrderBlotter> AllOrderBlotter(){
        return orderBlotterDao.AllOrderBlotters();
    }

}
