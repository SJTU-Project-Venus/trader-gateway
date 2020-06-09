package com.sjtu.trade.dto;

import java.io.Serializable;

public class LimitOrderDTO implements Serializable {
    private String futureName;
    private String otherId;
    private String side;
    private int   totalCount;
    private String traderName;
    private double unitPrice;
    private String traderDetailName;

    public String getTraderDetailName() {
        return traderDetailName;
    }

    public void setTraderDetailName(String traderDetailName) {
        this.traderDetailName = traderDetailName;
    }

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
