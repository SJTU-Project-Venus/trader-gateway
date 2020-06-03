package com.sjtu.trade.entity;

import java.util.List;

public class OrderBook {
    private Long orderId;
    private List<OrderBookItem> buyerSides;
    private List<OrderBookItem> sellerSides;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderBookItem> getBuyerSides() {
        return buyerSides;
    }

    public void setBuyerSides(List<OrderBookItem> buyerSides) {
        this.buyerSides = buyerSides;
    }

    public List<OrderBookItem> getSellerSides() {
        return sellerSides;
    }

    public void setSellerSides(List<OrderBookItem> sellerSides) {
        this.sellerSides = sellerSides;
    }
}
