package com.sjtu.trade.utils;

import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderSingle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TWapUtil {
    public List<OrderSingle> Twap(Order order){
        return new ArrayList<>();
    }
}
