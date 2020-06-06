package com.sjtu.trade.scheduler;

import com.sjtu.trade.serviceimpl.OrderBlotterService;
import com.sjtu.trade.serviceimpl.OrderBookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final OrderBlotterService orderBlotterService;

    private final OrderBookService orderBookService;
    Scheduler(OrderBlotterService greetingService,OrderBookService orderBookService) {
        this.orderBlotterService = greetingService;
        this.orderBookService = orderBookService;
    }

    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
    public void schedulingTask() {
        orderBlotterService.sendMessages();
        orderBookService.sendMessages();
    }
}