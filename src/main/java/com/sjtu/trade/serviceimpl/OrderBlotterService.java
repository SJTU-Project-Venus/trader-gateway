package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.repository.OrderBlotterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBlotterService  {

    @Autowired
    private OrderBlotterRepository orderBlotterRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRABSFER_DESTINATION = "/topic/orderBlotter";
    private List<NameDTO> userNames = new ArrayList<>();

    OrderBlotterService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessages(){
       for(NameDTO nameDTO:userNames){
           simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,new OrderBlotter());
       }
    }
    public void addUsers(NameDTO nameDTO){
        userNames.add(nameDTO);
    }

    public List<OrderBlotter> findByUserId(Long id){
        return orderBlotterRepository.findByInitiatorId(id);
    }
}
