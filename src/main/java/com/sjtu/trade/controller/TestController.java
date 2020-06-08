package com.sjtu.trade.controller;

import com.sjtu.trade.dto.PostRequestDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/order/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }
    @PostMapping("/order/postTest")
    public String getOrder(@RequestBody PostRequestDTO postRequestDTO) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        return "order id : ";
    }

}