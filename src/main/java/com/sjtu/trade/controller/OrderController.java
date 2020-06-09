package com.sjtu.trade.controller;

import com.sjtu.trade.dto.FrontOrderWebsocketDTO;
import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.dto.OrderBlotterDTO;
import com.sjtu.trade.dto.OrderWebsocketDTO;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.service.OrderService;
import com.sjtu.trade.serviceimpl.OrderWebsocketService;
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
public class OrderController {

    @Autowired
    private OrderWebsocketService orderWebsocketService;

    @Autowired
    private OrderService orderService;
    @ApiOperation(value = "用户下订单", notes = "")
    @RequestMapping(value = "/order/create", method = { RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> create(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.CreateOrder(order));
    }


    @ApiOperation(value = "用户下订单", notes = "")
    @RequestMapping(value = "/order/history/{name}", method = { RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> history(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.HistoryOrder(name));
    }

    @ApiOperation(value = "用户查看")
    @MessageMapping("/pendingOrder")
    @SendToUser("/topic/pendingOrder")
    public String greeting(FrontOrderWebsocketDTO message, Principal principal) throws Exception {
        System.out.print("Received greeting message "+ message.getName() +" from "+principal.getName());
        orderWebsocketService.addUsers(new OrderWebsocketDTO(principal.getName(),message.getName()));
        Thread.sleep(1000); // simulated delay
        return "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!";
    }
}
