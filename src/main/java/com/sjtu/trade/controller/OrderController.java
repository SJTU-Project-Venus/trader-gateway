package com.sjtu.trade.controller;

import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @ApiOperation(value = "用户下订单", notes = "")
    @RequestMapping(value = "/create", method = { RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> create(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.CreateOrder(order));
    }

}
