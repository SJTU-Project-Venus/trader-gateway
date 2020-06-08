package com.sjtu.trade.utils;

import com.sjtu.trade.dto.LimitOrderDTO;
import com.sjtu.trade.dto.MarketOrderDTO;
import com.sjtu.trade.dto.StopOrderDTO;
import com.sjtu.trade.entity.CancelOrderDTO;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderSingle;
import com.sjtu.trade.repository.OrderRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSendService {


    @Autowired
    private OrderRepository orderRepository;

    @Async
    public void TWAP_MARKET(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/marketOrders";
        try {

            //Thread.sleep(1000);
            int totalNumber = order.getNumber();
            int currentSent = 0;
            int step = totalNumber / (3600 * 8);
            for (; currentSent < totalNumber - step; currentSent += step) {
                Thread.sleep(60 * 1000);

                MarketOrderDTO marketOrder = new MarketOrderDTO();
                marketOrder.setFutureName(order.getFutureName());
                marketOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    marketOrder.setSide("BUYER");
                else
                    marketOrder.setSide("SELLER");

                marketOrder.setTotalCount(step);
                marketOrder.setTraderName(order.getTraderName());

                restTemplate.postForObject(url, marketOrder, JSONObject.class);
            }
            if(currentSent<totalNumber){
                Thread.sleep(60 * 1000);

                MarketOrderDTO marketOrder = new MarketOrderDTO();
                marketOrder.setFutureName(order.getFutureName());
                marketOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    marketOrder.setSide("BUYER");
                else
                    marketOrder.setSide("SELLER");

                marketOrder.setTotalCount(totalNumber-currentSent);
                marketOrder.setTraderName(order.getTraderName());

                restTemplate.postForObject(url, marketOrder, JSONObject.class);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Async
    public void TWAP_CANCEL(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/cancelOrders";

        CancelOrderDTO cancelOrder = new CancelOrderDTO();

        cancelOrder.setFutureName(order.getFutureName());
        cancelOrder.setTargetId(order.getOrderId().toString());
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
        restTemplate.postForObject(url,cancelOrder,JSONObject.class);

    }

    @Async
    public void TWAP_STOP(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/stopOrders";
        try {

            //Thread.sleep(1000);
            int totalNumber = order.getNumber();
            int currentSent = 0;
            int step = totalNumber / (3600 * 8);
            for (; currentSent < totalNumber - step; currentSent += step) {
                Thread.sleep(60 * 1000);

                StopOrderDTO stopOrder = new StopOrderDTO();
                stopOrder.setFutureName(order.getFutureName());
                stopOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    stopOrder.setSide("BUYER");
                else
                    stopOrder.setSide("SELLER");

                stopOrder.setTotalCount(step);
                stopOrder.setTraderName(order.getTraderName());
                stopOrder.setUnitPrice(order.getUnitPrice());

                stopOrder.setStopPrice(order.getStopPrice());

                switch (order.getTargetType()){
                    case MARKET:
                    {
                        stopOrder.setTargetType("MarketOrder");
                        break;
                    }
                    case STOP:{
                        stopOrder.setTargetType("StopOrder");
                        break;
                    }
                    case CANCEL:{
                        stopOrder.setTargetType("CancelOrder");
                        break;
                    }
                    case LIMIT:{
                        stopOrder.setTargetType("LimitOrder");
                        break;
                    }
                }
                restTemplate.postForObject(url, stopOrder, JSONObject.class);
            }
            if(currentSent<totalNumber){
                Thread.sleep(60 * 1000);

                StopOrderDTO stopOrder = new StopOrderDTO();
                stopOrder.setFutureName(order.getFutureName());
                stopOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    stopOrder.setSide("BUYER");
                else
                    stopOrder.setSide("SELLER");

                stopOrder.setTotalCount(totalNumber-currentSent);
                stopOrder.setTraderName(order.getTraderName());
                stopOrder.setUnitPrice(order.getUnitPrice());

                stopOrder.setStopPrice(order.getStopPrice());

                switch (order.getTargetType()){
                    case MARKET:
                    {
                        stopOrder.setTargetType("MarketOrder");
                        break;
                    }
                    case STOP:{
                        stopOrder.setTargetType("StopOrder");
                        break;
                    }
                    case CANCEL:{
                        stopOrder.setTargetType("CancelOrder");
                        break;
                    }
                    case LIMIT:{
                        stopOrder.setTargetType("LimitOrder");
                        break;
                    }
                }
                restTemplate.postForObject(url, stopOrder, JSONObject.class);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void TWAP_LIMITTED(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/limitOrders";
        try {

            //Thread.sleep(1000);
            int totalNumber = order.getNumber();
            int currentSent = 0;
            int step = totalNumber / (3600 * 8);
            for (; currentSent < totalNumber - step; currentSent += step) {
                Thread.sleep(60 * 1000);

                LimitOrderDTO limitOrder = new LimitOrderDTO();
                limitOrder.setFutureName(order.getFutureName());
                limitOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    limitOrder.setSide("BUYER");
                else
                    limitOrder.setSide("SELLER");

                limitOrder.setTotalCount(step);
                limitOrder.setTraderName(order.getTraderName());
                limitOrder.setUnitPrice(order.getUnitPrice());
                restTemplate.postForObject(url, limitOrder, JSONObject.class);
            }
            if(currentSent<totalNumber){
                Thread.sleep(60 * 1000);

                LimitOrderDTO limitOrder = new LimitOrderDTO();
                limitOrder.setFutureName(order.getFutureName());
                limitOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    limitOrder.setSide("BUYER");
                else
                    limitOrder.setSide("SELLER");

                limitOrder.setTotalCount(totalNumber-currentSent);
                limitOrder.setTraderName(order.getTraderName());
                limitOrder.setUnitPrice(order.getUnitPrice());
                restTemplate.postForObject(url, limitOrder, JSONObject.class);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

