package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderDao;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.repository.OrderRepository;
import com.sjtu.trade.service.IDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IDService idService;
    @Override
    public boolean Create(Order order){
        order.setId(idService.generate("order"));
        orderRepository.save(order);
        return true;
    }
}
