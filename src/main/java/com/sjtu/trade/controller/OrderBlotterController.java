package com.sjtu.trade.controller;

import com.sjtu.trade.service.OrderBlotterService;
import com.sjtu.trade.service.OrderBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderBlotter")
public class OrderBlotterController {
    @Autowired
    private OrderBlotterService orderBlotterService;

    @ApiOperation(value = "用户查看Market Depth", notes = "通过futureId")
    @RequestMapping(value = "/all", method = { RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(HttpStatus.OK).body(orderBlotterService.AllOrderBlotter());
    }
}
