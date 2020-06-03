package com.sjtu.trade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Broker {
    private Long id;
    private String brokerName;
    private String brokerHttp;
    private String brokerWs;


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

    public String getBrokerHttp() {
        return brokerHttp;
    }

    public void setBrokerHttp(String brokerHttp) {
        this.brokerHttp = brokerHttp;
    }

    public String getBrokerWs() {
        return brokerWs;
    }

    public void setBrokerWs(String brokerWs) {
        this.brokerWs = brokerWs;
    }
}
