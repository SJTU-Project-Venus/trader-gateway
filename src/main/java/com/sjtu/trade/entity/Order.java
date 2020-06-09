package com.sjtu.trade.entity;

import com.sjtu.trade.utils.OrderType;
import com.sjtu.trade.utils.SideType;
import com.sjtu.trade.utils.StatusType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
   // 实现一定程度的数据冗余减少设计的难度

    // 各种类型订单都应该有的
    @Id
    private  Long id;
    private OrderType orderType;
    private String traderName; // 交易员姓名
    private String traderCompany; // 交易公司名称
    private String brokerName;
    private StatusType Status;
    private int timestamp; // 发起时间

    // Market Order 所拥有的
    private SideType side;  // 买卖操作
    private String futureName; // 期货类型
    private int number; // 数量
    private double unitPrice; // 单价
    private int pendingNumber; // 未交易的数量

    // Limit Order
    // 买卖操作采用 Market Order 的那个side
    // 期货类型采用Market Order 的那个 futureName
    // 数量采用 Market Order 的那个 number
    // 单价采用Market depth 的那个unitPrice
    // 未交易的数量采用的是Market Order 的那个 pendingNumber;

    // Stop Order
    // 买卖操作采用 Market Order 的那个side
    // 期货类型采用Market Order 的那个 futureName
    // 数量采用 Market Order 的那个 number
    // 单价采用Market depth 的那个unitPrice
    // 未交易的数量采用的是Market Order 的那个 pendingNumber;
    private double stopPrice; // price 达到这个值的时候会转化
    private OrderType targetType; // 转化的类型

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    // Cancel Order
    private Long orderId; // 撤销订单的Id号。
    public StatusType getStatus() {
        return Status;
    }

    public void setStatus(StatusType status) {
        Status = status;
    }

    public int getPendingNumber() {
        return pendingNumber;
    }

    public void setPendingNumber(int pendingNumber) {
        this.pendingNumber = pendingNumber;
    }


    public String getTraderCompany() {
        return traderCompany;
    }

    public void setTraderCompany(String traderCompany) {
        this.traderCompany = traderCompany;
    }

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
