package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dao.OrderDao;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    /*
    注： CreateOrder()的数据一方面送入mongodb的数据库，一方面通过一定的策略扔到消息队列中。
     */
    @Async
    @Override
    public  boolean CreateOrder(Order order){
        try{
            return orderDao.Create(order);}
        catch (Exception e){
            return true;
        }
    }

    @Override
    public List<Order> PendingOrder(String traderName){
        return orderDao.findByUserPendingOrder(traderName);
    }

    @Override
    public List<Order> HistoryOrder(String traderName){
        return orderDao.findByUserDoneOrder(traderName);
    }

}
