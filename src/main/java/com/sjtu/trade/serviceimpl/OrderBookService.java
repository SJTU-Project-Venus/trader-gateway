package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.entity.OrderBook;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBookService {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRABSFER_DESTINATION = "/topic/orderBook";
    private List<NameDTO> userNames = new ArrayList<>();
    OrderBookService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessages(){
        for(NameDTO nameDTO:userNames){
            simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,new OrderBook());
        }
    }

    public void addUsers(NameDTO nameDTO){
        userNames.add(nameDTO);
    }
}
