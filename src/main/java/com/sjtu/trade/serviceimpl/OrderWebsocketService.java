package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.dto.OrderWebsocketDTO;
import com.sjtu.trade.dto.PendingOrderDTO;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderWebsocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final OrderService orderService;
    private static final String WS_MESSAGE_TRABSFER_DESTINATION = "/topic/pendingOrder";
    private List<OrderWebsocketDTO> userNames = new ArrayList<>();

    OrderWebsocketService(SimpMessagingTemplate simpMessagingTemplate,OrderService orderService){
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.orderService = orderService;
    }

    public void sendMessages(){
        try{
            for(OrderWebsocketDTO nameDTO:userNames){
                    List<Order> templist =  orderService.PendingOrder(nameDTO.getName());
                    List<PendingOrderDTO> resultList = new ArrayList<>();
                    for(Order order:templist){
                        PendingOrderDTO pendingOrderDTO = new PendingOrderDTO();
                        pendingOrderDTO.setFutureName(order.getFutureName());
                        pendingOrderDTO.setNumber(order.getPendingNumber());
                        pendingOrderDTO.setTimestamp(order.getTimestamp());
                        pendingOrderDTO.setOrderType(order.getOrderType());
                        pendingOrderDTO.setOrderId(order.getId());
                        resultList.add(pendingOrderDTO);
                    }
                    simpMessagingTemplate.convertAndSendToUser(nameDTO.getSessionId(),WS_MESSAGE_TRABSFER_DESTINATION,resultList);
            }
        }catch (Exception e){

        }

    }

    public void addUsers(OrderWebsocketDTO orderWebsocketDTO){
        userNames.add(orderWebsocketDTO);
    }
}
