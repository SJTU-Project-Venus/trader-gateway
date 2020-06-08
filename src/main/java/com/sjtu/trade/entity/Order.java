package com.sjtu.trade.entity;

import com.sjtu.trade.utils.OrderType;
import com.sjtu.trade.utils.SideType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
   // 实现一定程度的数据冗余减少设计的难度

    // 各种类型订单都应该有的
    @Id
    private  Long id;
    private OrderType orderType;
    private String traderName;
    private String brokerName;


    // Market Order 所拥有的
    private SideType side;  // 买卖操作
    private String futureName; // 期货类型
    private int number; // 数量

    // Limit Order  Stop Order 所拥有的
   // 买卖操作采用 Market Order 的那个side
    // 期货类型采用Market Order 的那个 futureId
    // 数量采用 Market Order 的那个 number
    private double unitPrice; // 止损价格;

    private double stopPrice;

    private OrderType targetType;


    public OrderType getTargetType() {
        return targetType;
    }

    public void setTargetType(OrderType targetType) {
        this.targetType = targetType;
    }

    public double getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    // Cancel Order
    private Long orderId; // 撤销订单的Id号。

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public SideType getSide() {
        return side;
    }

    public void setSide(SideType side) {
        this.side = side;
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



    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
