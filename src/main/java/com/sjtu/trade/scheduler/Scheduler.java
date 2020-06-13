package com.sjtu.trade.scheduler;

import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.serviceimpl.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final OrderWebsocketService orderWebsocketService;
    private final OrderBlotterService orderBlotterService;
    private final WebsocketClientServiceN websocketClientServiceN;
    private final WebsocketClientServiceM websocketClientServiceM;
    Scheduler(OrderWebsocketService orderWebsocketService,WebsocketClientServiceM websocketClientServiceM,OrderBlotterService orderBlotterService,WebsocketClientServiceN websocketClientServiceN) {
        this.orderWebsocketService = orderWebsocketService;
        this.orderBlotterService = orderBlotterService;
        this.websocketClientServiceN = websocketClientServiceN;
        this.websocketClientServiceM = websocketClientServiceM;
    }

    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
    public void schedulingTask() {
        orderWebsocketService.sendMessages();
        orderBlotterService.sendMessages();
        websocketClientServiceN.heartFelt();
        websocketClientServiceM.heartFelt();
    }
}