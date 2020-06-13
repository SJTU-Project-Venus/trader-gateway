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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSendService {


    @Autowired
    private OrderRepository orderRepository;

    @Value("${brokerHttpN}")
    private String URLN;

    @Value("${brokerHttpM}")
    private String URLM;

    @Async
    public void TWAP_MARKET(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "";
        if(order.getBrokerName().equals("N"))
            url = URLN+"/marketOrders";
        else
            url = URLM+"/marketOrders";
        try {

            //Thread.sleep(1000);
            int totalNumber = order.getNumber();
            int currentSent = 0;
            int currentStep = 0;
            int step = totalNumber / (100);
            for (; currentStep < step; currentStep ++) {
                Thread.sleep(100);

                MarketOrderDTO marketOrder = new MarketOrderDTO();
                marketOrder.setTraderDetailName(order.getTraderName());
                marketOrder.setFutureName(order.getFutureName());
                marketOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    marketOrder.setSide("BUYER");
                else
                    marketOrder.setSide("SELLER");

                marketOrder.setTotalCount(100);
                currentSent +=100;
                marketOrder.setTraderName(order.getTraderCompany());

                JSONObject result = restTemplate.postForObject(url, marketOrder, JSONObject.class);
                //System.out.println(result);
            }
            if(currentSent<totalNumber){
                Thread.sleep(100);

                MarketOrderDTO marketOrder = new MarketOrderDTO();
                marketOrder.setTraderDetailName(order.getTraderName());
                marketOrder.setFutureName(order.getFutureName());
                marketOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    marketOrder.setSide("BUYER");
                else
                    marketOrder.setSide("SELLER");

                marketOrder.setTotalCount(totalNumber-currentSent);
                marketOrder.setTraderName(order.getTraderCompany());

                JSONObject result = restTemplate.postForObject(url, marketOrder, JSONObject.class);
                //System.out.println(result);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Async
    public void TWAP_CANCEL(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "";
        if(order.getBrokerName().equals("N"))
            url = URLN+"/cancelOrders";
        else
            url = URLM+"/cancelOrders";
        CancelOrderDTO cancelOrder = new CancelOrderDTO();

        cancelOrder.setFutureName(order.getFutureName());
        cancelOrder.setTargetId(order.getOrderId().toString());
        cancelOrder.setTraderDetailName(order.getTraderName());
        Order order1 = orderRepository.findById(order.getOrderId()).get();
        order1.setStatus(StatusType.DONE);
        orderRepository.save(order1);
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
        //System.out.println(result);

    }

    @Async
    public void TWAP_STOP(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "";
        if(order.getBrokerName().equals("N"))
            url = URLN+"/stopOrders";
        else
            url = URLM+"/stopOrders";
        try {

            //Thread.sleep(1000);
            int totalNumber = order.getNumber();
            int currentSent = 0;
            int currentStep = 0;
            int step = totalNumber / (100);
            for (; currentStep < step; currentStep++) {
                Thread.sleep(100);

                StopOrderDTO stopOrder = new StopOrderDTO();
                stopOrder.setFutureName(order.getFutureName());
                stopOrder.setTraderDetailName(order.getTraderName());
                stopOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    stopOrder.setSide("BUYER");
                else
                    stopOrder.setSide("SELLER");

                stopOrder.setTotalCount(100);
                currentSent+=100;
                stopOrder.setTraderName(order.getTraderCompany());
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
                JSONObject result = restTemplate.postForObject(url, stopOrder, JSONObject.class);
                //System.out.println(result);
            }
            if(currentSent<totalNumber){
                Thread.sleep( 100);

                StopOrderDTO stopOrder = new StopOrderDTO();
                stopOrder.setFutureName(order.getFutureName());
                stopOrder.setTraderDetailName(order.getTraderName());
                stopOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    stopOrder.setSide("BUYER");
                else
                    stopOrder.setSide("SELLER");

                stopOrder.setTotalCount(totalNumber-currentSent);
                stopOrder.setTraderName(order.getTraderCompany());
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
                JSONObject result = restTemplate.postForObject(url, stopOrder, JSONObject.class);
                //System.out.println(result);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void TWAP_LIMITTED(Order order) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "";
        if(order.getBrokerName().equals("N"))
            url = URLN+"/limitOrders";
        else
            url = URLM+"/limitOrders";
        try {

            //Thread.sleep(1000);
            int totalNumber = order.getNumber();
            int currentSend = 0;
            int currentSent=0;
            int step = totalNumber / (100);
            for (; currentSend<step;currentSend++) {
                Thread.sleep(100);

                LimitOrderDTO limitOrder = new LimitOrderDTO();
                limitOrder.setTraderDetailName(order.getTraderName());
                limitOrder.setFutureName(order.getFutureName());
                limitOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    limitOrder.setSide("BUYER");
                else
                    limitOrder.setSide("SELLER");

                limitOrder.setTotalCount(100);
                limitOrder.setTraderName(order.getTraderCompany());
                limitOrder.setUnitPrice(order.getUnitPrice());
                JSONObject result =  restTemplate.postForObject(url, limitOrder, JSONObject.class);
                currentSent+=100;
                //System.out.println(result);
            }
            if(currentSent<totalNumber){
                Thread.sleep(100);

                LimitOrderDTO limitOrder = new LimitOrderDTO();
                limitOrder.setTraderDetailName(order.getTraderName());
                limitOrder.setFutureName(order.getFutureName());
                limitOrder.setOtherId(order.getId().toString());
                if (order.getSide() == SideType.BUY)
                    limitOrder.setSide("BUYER");
                else
                    limitOrder.setSide("SELLER");

                limitOrder.setTotalCount(totalNumber-currentSent);
                limitOrder.setTraderName(order.getTraderCompany());
                limitOrder.setUnitPrice(order.getUnitPrice());
                JSONObject result =  restTemplate.postForObject(url, limitOrder, JSONObject.class);
                //System.out.println(result);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

