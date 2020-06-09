package com.sjtu.trade.entity;

public class CancelOrderDTO {
    private String futureName;
    private String targetId;
    private String targetType;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
