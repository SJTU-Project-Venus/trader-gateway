package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.repository.OrderBlotterRepository;
import com.sjtu.trade.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderBlotterService  {

    @Autowired
    private OrderBlotterDao orderBlotterDao;

    @Autowired
    private RedisUtil redisUtil;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRABSFER_DESTINATION = "/topic/orderBlotter";
    private List<NameDTO> userNames = new ArrayList<>();

    OrderBlotterService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessages(){
        for(NameDTO nameDTO:userNames){
            List<Object> templist = redisUtil.lGet(nameDTO.getFutureName(),0,-1);
            if(templist!=null){
            Collections.reverse(templist);

            if(templist.size()<10){

                simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,templist);
            }
            else{
                templist = templist.subList(0,10);
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

    public List<OrderBlotter> historyOrderBlotters(String name){
        return orderBlotterDao.HistoryUsername(name);
    }
    public List<OrderBlotter> findByUserId(Long id){
        return new ArrayList<>();
    }
}
