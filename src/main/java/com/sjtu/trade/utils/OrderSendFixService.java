package com.sjtu.trade.utils;

import com.sjtu.trade.dto.LimitOrderDTO;
import com.sjtu.trade.dto.MarketOrderDTO;
import com.sjtu.trade.dto.StopOrderDTO;
import com.sjtu.trade.entity.CancelOrderDTO;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.fix.FixClient;
import com.sjtu.trade.repository.OrderRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix50sp2.NewOrderSingle;

import java.awt.print.Book;
import java.time.LocalDateTime;

@Component
public class OrderSendFixService {

    @Value("${traderCompany}")
    private String traderCompany;
    @Autowired
    private FixClient fixClient;

        @Autowired
        private OrderRepository orderRepository;

        @Async
        public void TWAP_MARKET(Order order) {

            SessionID sessionID = fixClient.sessionIds().get(0);
            try {
                int totalNumber = order.getNumber();
                int currentSent = 0;
                int currentStep = 0;
                int step = totalNumber / (100);
                for (; currentStep < step; currentStep ++) {
                    Thread.sleep(1000);
                    Side side = null;
                    if (order.getSide() == SideType.BUY)
                        side = new Side(Side.BUY);
                    else
                        side = new Side(Side.SELL);

                    // otherId,side,traderName,traderCompany,futureName,totalNumber
                    NewOrderSingle orderSingle = new NewOrderSingle();
                    orderSingle.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
                    orderSingle.getHeader().setField(new SenderCompID(order.getTraderCompany()));// traderCompany
                    orderSingle.getHeader().setField(new SenderSubID(order.getTraderName()));// traderName
                    orderSingle.set(new ClOrdID(order.getId().toString()));//otherId
                    orderSingle.set(side);// side
                    orderSingle.set(new Symbol(order.getFutureName()));// futureName
                    orderSingle.set(new OrderQty(100));// totalNumber
                    orderSingle.set(new OrdType(OrdType.MARKET));// Market Type
                    try {
                        Session.sendToTarget(orderSingle);
                    } catch (SessionNotFound e) {
                        e.printStackTrace();
                    }
                }
                if(currentSent<totalNumber){
                    Thread.sleep(1000);
                    Side side = null;
                    if (order.getSide() == SideType.BUY)
                        side = new Side(Side.BUY);
                    else
                        side = new Side(Side.SELL);

                    // otherId,side,traderName,traderCompany,futureName,totalNumber
                    NewOrderSingle orderSingle = new NewOrderSingle();
                    orderSingle.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
                    orderSingle.getHeader().setField(new SenderCompID(order.getTraderCompany()));
                    orderSingle.getHeader().setField(new SenderSubID(order.getTraderName()));
                    orderSingle.set(new ClOrdID(order.getId().toString()));
                    orderSingle.set(new OrdType(OrdType.MARKET));
                    orderSingle.set(side);
                    orderSingle.set(new Symbol(order.getFutureName()));
                    orderSingle.set(new OrderQty(totalNumber-currentSent));
                    try {
                        Session.sendToTarget(orderSingle);
                    } catch (SessionNotFound e) {
                        e.printStackTrace();
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        @Async
        public void TWAP_CANCEL(Order order) {

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/cancelOrders";

            CancelOrderDTO cancelOrder = new CancelOrderDTO();

            cancelOrder.setFutureName(order.getFutureName());
            cancelOrder.setTargetId(order.getOrderId().toString());
            cancelOrder.setTraderDetailName(order.getTraderName());
            Order order1 = orderRepository.findById(order.getOrderId()).get();
            switch (order1.getOrderType()){
                case MARKET:
                {
                    cancelOrder.setTargetType("MarketOrder");
                    break;
                }
                case STOP:{
                    cancelOrder.setTargetType("StopOrder");
                    break;
                }
                case CANCEL:{
                    cancelOrder.setTargetType("CancelOrder");
                    break;
                }
                case LIMIT:{
                    cancelOrder.setTargetType("LimitOrder");
                    break;
                }
            }
            JSONObject result = restTemplate.postForObject(url,cancelOrder,JSONObject.class);
            System.out.println(result);

        }

        @Async
        public void TWAP_STOP(Order order) {

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/stopOrders";
            try {

                //Thread.sleep(1000);
                int totalNumber = order.getNumber();
                int currentSent = 0;
                int currentStep = 0;
                int step = totalNumber / (100);
                for (; currentStep < step; currentStep++) {
                    Thread.sleep(1000);
                    Side side = null;
                    if (order.getSide() == SideType.BUY)
                        side = new Side(Side.BUY);
                    else
                        side = new Side(Side.SELL);

                    // otherId,side,traderName,traderCompany,futureName,totalNumber,unitPrice,stopPrice,targetType
                    NewOrderSingle orderSingle = new NewOrderSingle();
                    orderSingle.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
                    orderSingle.getHeader().setField(new SenderCompID(order.getTraderCompany()));//traderCompany
                    orderSingle.getHeader().setField(new SenderSubID(order.getTraderName()));//traderName
                    orderSingle.set(new ClOrdID(order.getId().toString()));//otherId
                    orderSingle.set(side);//side
                    orderSingle.set(new Symbol(order.getFutureName()));//futureName
                    orderSingle.set(new OrderQty(100));//totalNumber
                    orderSingle.set(new Price(order.getUnitPrice()));//unitPrice
                    orderSingle.set(new StopPx(order.getStopPrice()));//stopPrice
                    orderSingle.set(new OrdType(OrdType.STOP_LIMIT));//Stop Type

                    BookingType orderType = new BookingType(OrdType.MARKET);
                    switch (order.getOrderType()){
                        case MARKET:
                        {
                            orderType = new BookingType(OrdType.MARKET);
                            break;
                        }
                        case STOP:{
                            orderType = new BookingType(OrdType.STOP_LIMIT);
                            break;
                        }
                        case LIMIT:{
                            orderType = new BookingType(OrdType.LIMIT);
                            break;
                        }
                    }
                    orderSingle.set(orderType);// targetType
                    try {
                        Session.sendToTarget(orderSingle);
                    } catch (SessionNotFound e) {
                        e.printStackTrace();
                    }
                }
                if(currentSent<totalNumber) {
                    Thread.sleep(1000);
                    Side side = null;
                    if (order.getSide() == SideType.BUY)
                        side = new Side(Side.BUY);
                    else
                        side = new Side(Side.SELL);

                    // otherId,side,traderName,traderCompany,futureName,totalNumber,unitPrice,stopPrice,targetType
                    NewOrderSingle orderSingle = new NewOrderSingle();
                    orderSingle.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
                    orderSingle.getHeader().setField(new SenderCompID(order.getTraderCompany()));
                    orderSingle.getHeader().setField(new SenderSubID(order.getTraderName()));
                    orderSingle.set(new ClOrdID(order.getId().toString()));
                    orderSingle.set(side);
                    orderSingle.set(new Symbol(order.getFutureName()));
                    orderSingle.set(new OrderQty(totalNumber-currentSent));
                    orderSingle.set(new Price(order.getUnitPrice()));
                    orderSingle.set(new StopPx(order.getStopPrice()));
                    orderSingle.set(new OrdType(OrdType.STOP_LIMIT));

                    BookingType orderType = new BookingType(OrdType.MARKET);
                    switch (order.getOrderType()){
                        case MARKET:
                        {
                            orderType = new BookingType(OrdType.MARKET);
                            break;
                        }
                        case STOP:{
                            orderType = new BookingType(OrdType.STOP_LIMIT);
                            break;
                        }
                        case LIMIT:{
                            orderType = new BookingType(OrdType.LIMIT);
                            break;
                        }
                    }
                    orderSingle.set(orderType);
                    try {
                        Session.sendToTarget(orderSingle);
                    } catch (SessionNotFound e) {
                        e.printStackTrace();
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Async
        public void TWAP_LIMITTED(Order order) {

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/limitOrders";
            try {

                //Thread.sleep(1000);
                int totalNumber = order.getNumber();
                int currentSend = 0;
                int currentSent=0;
                int step = totalNumber / (100);
                for (; currentSend<step;currentSend++) {
                    Thread.sleep(1000);
                    Side side = null;
                    if (order.getSide() == SideType.BUY)
                        side = new Side(Side.BUY);
                    else
                        side = new Side(Side.SELL);

                    // otherId,side,traderName,traderCompany,futureName,totalNumber,unitPrice
                    NewOrderSingle orderSingle = new NewOrderSingle();
                    orderSingle.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
                    orderSingle.getHeader().setField(new SenderCompID(order.getTraderCompany()));//traderCompany
                    orderSingle.getHeader().setField(new SenderSubID(order.getTraderName()));//traderName
                    orderSingle.set(new ClOrdID(order.getId().toString()));//otherId
                    orderSingle.set(side);//side
                    orderSingle.set(new Symbol(order.getFutureName()));//futureName
                    orderSingle.set(new OrderQty(100));//totalNumber
                    orderSingle.set(new OrdType(OrdType.LIMIT));//Limit Type
                    orderSingle.set(new Price(order.getUnitPrice()));//unitPrice
                    try {
                        Session.sendToTarget(orderSingle);
                    } catch (SessionNotFound e) {
                        e.printStackTrace();
                    }
                }
                if(currentSent<totalNumber){
                    Thread.sleep(1000);
                    Side side = null;
                    if (order.getSide() == SideType.BUY)
                        side = new Side(Side.BUY);
                    else
                        side = new Side(Side.SELL);

                    // otherId,side,traderName,traderCompany,futureName,totalNumber,unitPrice
                    NewOrderSingle orderSingle = new NewOrderSingle();
                    orderSingle.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
                    orderSingle.getHeader().setField(new SenderCompID(order.getTraderCompany()));
                    orderSingle.getHeader().setField(new SenderSubID(order.getTraderName()));
                    orderSingle.set(new ClOrdID(order.getId().toString()));
                    orderSingle.set(side);
                    orderSingle.set(new Symbol(order.getFutureName()));
                    orderSingle.set(new OrderQty(totalNumber-currentSent));
                    orderSingle.set(new OrdType(OrdType.LIMIT));
                    orderSingle.set(new Price(order.getUnitPrice()));
                    try {
                        Session.sendToTarget(orderSingle);
                    } catch (SessionNotFound e) {
                        e.printStackTrace();
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}

