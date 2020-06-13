package com.sjtu.trade.entity;

public class OrderBlotterEntity {
    private String id;
    private int count;
    private int price;
    private String creationTime;
    private String buyerTraderName;
    private String sellerTraderName;
    private String buyerOrderId;
    private String sellerOrderId;
    private String marketDepthId;
    private String buyerTraderDetailName;
    private String sellerTraderDetailName;
    private String buyerOtherId;
    private String sellerOtherId;
    private String futureName;

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getBuyerTraderName() {
        return buyerTraderName;
    }

    public void setBuyerTraderName(String buyerTraderName) {
        this.buyerTraderName = buyerTraderName;
    }

    public String getSellerTraderName() {
        return sellerTraderName;
    }

    public void setSellerTraderName(String sellerTraderName) {
        this.sellerTraderName = sellerTraderName;
    }

    public String getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(String buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public String getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(String sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public String getBuyerTraderDetailName() {
        return buyerTraderDetailName;
    }

    public void setBuyerTraderDetailName(String buyerTraderDetailName) {
        this.buyerTraderDetailName = buyerTraderDetailName;
    }

    public String getSellerTraderDetailName() {
        return sellerTraderDetailName;
    }

    public void setSellerTraderDetailName(String sellerTraderDetailName) {
        this.sellerTraderDetailName = sellerTraderDetailName;
    }

    public String getBuyerOtherId() {
        return buyerOtherId;
    }

    public void setBuyerOtherId(String buyerOtherId) {
        this.buyerOtherId = buyerOtherId;
    }

    public String getSellerOtherId() {
        return sellerOtherId;
    }

    public void setSellerOtherId(String sellerOtherId) {
        this.sellerOtherId = sellerOtherId;
    }
}