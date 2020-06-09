package com.sjtu.trade.dto;

public class OrderWebsocketDTO {
    private String SessionId="";
    private String name;

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public OrderWebsocketDTO(String id, String futureId){
        this.name = futureId;
        this.SessionId = id;
    }
}
