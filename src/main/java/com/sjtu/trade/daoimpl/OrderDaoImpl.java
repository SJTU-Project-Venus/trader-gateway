package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderDao;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.repository.OrderRepository;
import com.sjtu.trade.service.IDService;
import com.sjtu.trade.utils.OrderSendService;
import com.sjtu.trade.utils.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IDService idService;

    @Autowired
    private OrderSendService orderSendService;


    public List<Order> findByUserPendingOrder(String name){
        List<Order> templist = orderRepository.findByTraderName(name);
        List<Order> resultList = new ArrayList<>();
        for(Order order:templist){
            if(order.getStatus()== StatusType.PENDING)
                resultList.add(order);
        }
        return resultList;
    }

    public List<Order> findByUserDoneOrder(String name){
        List<Order> templist = orderRepository.findByTraderName(name);
        List<Order> resultList = new ArrayList<>();
        for(Order order:templist){
            if(order.getStatus()== StatusType.DONE)
                resultList.add(order);
        }
        return resultList;
    }
    @Override
    public boolean Create(Order order){
        order.setId(idService.generate("order"));
        switch (order.getOrderType()){
            case MARKET:{
                orderSendService.TWAP_MARKET(order);
            }
            case LIMIT:{
                orderSendService.TWAP_LIMITTED(order);
            }
            case STOP:{
                orderSendService.TWAP_STOP(order);
            }
            case CANCEL:{
                orderSendService.TWAP_CANCEL(order);
            }
        }
        orderRepository.save(order);
        return true;
    }
}
