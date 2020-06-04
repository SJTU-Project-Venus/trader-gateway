package com.sjtu.trade.controller;

import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.entity.TraderUser;
import com.sjtu.trade.service.OrderBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderBook")
public class OrderBookController {

    @Autowired
    private OrderBookService orderBookService;

    @ApiOperation(value = "用户查看Market Depth", notes = "通过futureId")
    @RequestMapping(value = "/marketDepth/{id}", method = { RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> login(@PathVariable("id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderBookService.findMarketDepth(id));
    }
}
