package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.repository.OrderBlotterRepository;
import com.sjtu.trade.repository.OrderRepository;
import com.sjtu.trade.utils.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component("orderBlotterDao")
public class OrderBlotterDaoImpl implements OrderBlotterDao {

    @Value("${traderCompany}")
    private String traderCompany;
    @Autowired
    private OrderBlotterRepository orderBlotterRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<OrderBlotter> AllOrderBlotters(){
        return orderBlotterRepository.findAll();
    }

    @Override
    public boolean Create(OrderBlotter orderBlotter){
        try{
            orderBlotterRepository.save(orderBlotter);
        if(orderBlotter.getBuyerTraderName().equals(traderCompany)){
            Order order = orderRepository.findById(Long.parseLong(orderBlotter.getBuyerOtherId())).get();

            int done = orderBlotter.getCount();
            int pending = order.getPendingNumber();
            if(done == pending){
                order.setPendingNumber(0);
                order.setStatus(StatusType.DONE);
                orderRepository.save(order);
            }
            else{
                order.setPendingNumber(pending-done);
                orderRepository.save(order);
            }
        }
        if(orderBlotter.getSellerTraderName().equals(traderCompany)){
            Order order = orderRepository.findById(Long.parseLong(orderBlotter.getSellerOtherId())).get();

            int done = orderBlotter.getCount();
            int pending = order.getPendingNumber();
            if(done == pending){
                order.setPendingNumber(0);
                order.setStatus(StatusType.DONE);
                orderRepository.save(order);
            }
            else{
                order.setPendingNumber(pending-done);
                orderRepository.save(order);
            }
        }
        return true;}
        catch (Exception e){
            System.out.println("orderBlotter Error");
            return true;
        }
    }

    @Override
    public List<OrderBlotter> HistoryUsername(String name){
        return orderBlotterRepository.findByBuyerTraderNameOrSellerTraderName(name,name);
    }
}
