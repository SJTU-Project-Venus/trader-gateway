package com.sjtu.trade.scheduler;

import com.sjtu.trade.serviceimpl.OrderBlotterService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final OrderBlotterService orderBlotterService;

    Scheduler(OrderBlotterService greetingService) {
        this.orderBlotterService = greetingService;
    }

    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
    public void schedulingTask() {
        orderBlotterService.sendMessages();
    }
}