package com.sjtu.trade.dto;

public class NameDTO {
    private String SessionId="";
    private String futureName;

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public NameDTO(String id, String futureId){
        this.futureName = futureId;
        this.SessionId = id;
    }
}
