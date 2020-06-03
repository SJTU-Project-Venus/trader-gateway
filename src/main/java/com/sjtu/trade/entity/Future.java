package com.sjtu.trade.entity;

public class Future {
    private Long id;
    private String futureName; // 全称（BOIL-SEP20）
    private String period; // 期限（SEP20）
    private String category; // 类别（石油）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
