package com.sjtu.trade.controller;

import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.dto.OrderBlotterDTO;
import com.sjtu.trade.serviceimpl.OrderBlotterService;
import com.sjtu.trade.serviceimpl.OrderBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@RestController
@RequestMapping("/orderBook")
public class OrderBookController {

    private final OrderBookService orderBookService;

    OrderBookController(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

    @ApiOperation(value = "用户查看Market Depth")
    @MessageMapping("/orderBook")
    @SendToUser("/topic/orderBook")
    public String greeting(OrderBlotterDTO message, Principal principal) throws Exception {
        System.out.print("Received greeting message "+ message.getFutureName() +" from "+principal.getName());
        orderBookService.addUsers(new NameDTO(principal.getName(),message.getFutureName()));
        Thread.sleep(1000); // simulated delay
        return "Hello, " + HtmlUtils.htmlEscape(message.getFutureName()) + "!";
    }

}
