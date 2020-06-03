package com.sjtu.trade.entity;

import com.sjtu.trade.utils.SideType;

public class OrderBlotter {

    private Long id;
    private String brokerName;
    private Long futureId;
    private String traderType = "Swap";
    private double price;
    private int number;

    private Long initiatorId;
    private SideType initiatorSide;

    private Long completionId;
    private SideType completionSide;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Long getFutureId() {
        return futureId;
    }

    public void setFutureId(Long futureId) {
        this.futureId = futureId;
    }

    public String getTraderType() {
        return traderType;
    }

    public void setTraderType(String traderType) {
        this.traderType = traderType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
        this.initiatorId = initiatorId;
    }

    public SideType getInitiatorSide() {
        return initiatorSide;
    }

    public void setInitiatorSide(SideType initiatorSide) {
        this.initiatorSide = initiatorSide;
    }

    public Long getCompletionId() {
        return completionId;
    }

    public void setCompletionId(Long completionId) {
        this.completionId = completionId;
    }

    public SideType getCompletionSide() {
        return completionSide;
    }

    public void setCompletionSide(SideType completionSide) {
        this.completionSide = completionSide;
    }
}
