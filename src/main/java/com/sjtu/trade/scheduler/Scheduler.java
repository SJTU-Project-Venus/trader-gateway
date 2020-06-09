package com.sjtu.trade.scheduler;

import com.sjtu.trade.serviceimpl.OrderBlotterService;
import com.sjtu.trade.serviceimpl.OrderBookService;
import com.sjtu.trade.serviceimpl.OrderWebsocketService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final OrderWebsocketService orderWebsocketService;

    Scheduler(OrderWebsocketService orderWebsocketService) {
        this.orderWebsocketService = orderWebsocketService;
    }

    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
    public void schedulingTask() {
        orderWebsocketService.sendMessages();
    }
}