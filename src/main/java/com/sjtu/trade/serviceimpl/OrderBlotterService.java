package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.repository.OrderBlotterRepository;
import com.sjtu.trade.repository.OrderRepository;
import com.sjtu.trade.utils.RedisUtil;
import com.sjtu.trade.utils.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

@Component("orderBlotterService")
public class OrderBlotterService  {

    @Autowired
    private RedisUtil redisUtil;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRABSFER_DESTINATION = "/topic/orderBlotter";
    private List<NameDTO> userNames = new ArrayList<>();

    OrderBlotterService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @Value("${traderCompany}")
    private String traderCompany;
    @Autowired
    private OrderBlotterRepository orderBlotterRepository;
    @Autowired
    private OrderRepository orderRepository;
    public List<OrderBlotter> AllOrderBlotters(){
        return orderBlotterRepository.findAll();
    }

    public synchronized boolean Create(OrderBlotter orderBlotter){
        try{
            orderBlotterRepository.save(orderBlotter);
            System.out.println(orderBlotter.getId());
            if(orderBlotter.getBuyerTraderName().equals(traderCompany)){
                Order order = orderRepository.findById(Long.parseLong(orderBlotter.getBuyerOtherId())).get();
                System.out.println("OrderId"+orderBlotter.getBuyerOtherId()+" "+orderBlotter.getSellerOtherId());
                int done = orderBlotter.getCount();
                int pending = order.getPendingNumber();
                int currentCount = pending-done;
                if(currentCount==0){
                    order.setPendingNumber(0);
                    order.setStatus(StatusType.DONE);
                    orderRepository.save(order);
                }
                else{
                    order.setPendingNumber(currentCount);
                    orderRepository.save(order);
                }
            }
            if(orderBlotter.getSellerTraderName().equals(traderCompany)){
                Order order1 = orderRepository.findById(Long.parseLong(orderBlotter.getSellerOtherId())).get();
                System.out.println("OrderId"+orderBlotter.getBuyerOtherId()+" "+orderBlotter.getSellerOtherId());
                int done = orderBlotter.getCount();
                int pending = order1.getPendingNumber();
                int currentCount = pending-done;
                if(currentCount==0){
                    order1.setPendingNumber(0);
                    order1.setStatus(StatusType.DONE);
                    orderRepository.save(order1);
                }
                else{
                    order1.setPendingNumber(currentCount);
                    orderRepository.save(order1);
                }
            }
            return true;}
        catch (Exception e){
           // System.out.println("orderBlotter Error");
            return true;
        }
    }

    public List<OrderBlotter> HistoryUsername(String name){
        return orderBlotterRepository.findByBuyerTraderNameOrSellerTraderName(name,name);
    }
    public void sendMessages(){
        for(NameDTO nameDTO:userNames){
            List<Object> templist = redisUtil.lGet(nameDTO.getFutureName(),0,-1);
            if(templist!=null){
            if(templist.size()<20){

                simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,templist);
            }
            else{
                templist = templist.subList(0,20);
                simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,templist);
            }
        }
            else {
                simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,new ArrayList<>());
            }
        }
    }
    public void addUsers(NameDTO nameDTO){
        userNames.add(nameDTO);
    }

}
