package com.sjtu.trade.dto;

import com.sjtu.trade.utils.OrderType;

public class PendingOrderDTO {
    private Long orderId;// 订单id
    private int number;  // 正在处理但是还未处理完的数量
    private String futureName; // 期货的名字
    private OrderType orderType; // 订单的类型
    private int timestamp; // 订单发起的时间

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
