package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.entity.OrderBook;
import com.sjtu.trade.repository.OrderBlotterRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component("orderBookService")
public class OrderBookService {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRABSFER_DESTINATION = "/topic/orderBook";
    private List<NameDTO> userNames = new ArrayList<>();
    OrderBookService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessages(String brokerName,String text){
        try{
            //System.out.println(text);
            JSONObject object = JSONObject.fromObject(text);
            String futureName = (String)object.get("futureName");
            object.put("brokerName",brokerName);
            //System.out.println(futureName);
            System.out.println(userNames.size());
            for(NameDTO nameDTO:userNames){
                System.out.println(nameDTO.getFutureName());
                if(futureName.equals(nameDTO.getFutureName())){
                    simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,object);
                    System.out.println(nameDTO.getFutureName());
                }
            }
        }catch (Exception e){

        }

    }

    public void addUsers(NameDTO nameDTO){
        userNames.add(nameDTO);
    }
}
