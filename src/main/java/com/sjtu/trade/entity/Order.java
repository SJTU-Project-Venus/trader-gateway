package com.sjtu.trade.entity;

import com.sjtu.trade.utils.OrderType;
import com.sjtu.trade.utils.SideType;

public class Order {
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

    // Cancel Order
    private Long orderId; // 撤销订单的Id号。

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public SideType getSide() {
        return side;
    }

    public void setSide(SideType side) {
        this.side = side;
    }

    public Long getFutureId() {
        return futureId;
    }

    public void setFutureId(Long futureId) {
        this.futureId = futureId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
