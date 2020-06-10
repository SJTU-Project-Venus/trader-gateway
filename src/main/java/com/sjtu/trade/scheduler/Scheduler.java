package com.sjtu.trade.scheduler;

import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.serviceimpl.OrderBlotterService;
import com.sjtu.trade.serviceimpl.OrderBookService;
import com.sjtu.trade.serviceimpl.OrderWebsocketService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final OrderWebsocketService orderWebsocketService;
    private final OrderBlotterService orderBlotterService;
    Scheduler(OrderWebsocketService orderWebsocketService,OrderBlotterService orderBlotterService) {
        this.orderWebsocketService = orderWebsocketService;
        this.orderBlotterService = orderBlotterService;
    }

    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
    public void schedulingTask() {
        orderWebsocketService.sendMessages();
        orderBlotterService.sendMessages();
    }
}