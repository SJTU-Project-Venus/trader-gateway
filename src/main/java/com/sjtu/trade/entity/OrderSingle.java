package com.sjtu.trade.entity;

import com.sjtu.trade.utils.OrderType;
import com.sjtu.trade.utils.SideType;

public class OrderSingle {
    // 实现一定程度的数据冗余减少设计的难度

    // 各种类型订单都应该有的
    private  Long id;
    private OrderType orderType;
    private Long traderId;
    private String brokerName;


    // Market Order 所拥有的
    private SideType side;  // 买卖操作
    private Long futureId; // 期货类型
    private int number; // 数量

    // Limit Order  Stop Order 所拥有的
    // 买卖操作采用 Market Order 的那个side
    // 期货类型采用Market Order 的那个 futureId
    // 数量采用 Market Order 的那个 number
    private double unitPrice; // 止损价格;

    // Cancel Order（没有这个东西）
}
