package com.sjtu.trade.dto;

public class NameDTO {
    private String SessionId="";
    private Long futureId;

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public Long getFutureId() {
        return futureId;
    }

    public void setFutureId(Long futureId) {
        this.futureId = futureId;
    }
    public NameDTO(String id,Long futureId){
        this.futureId = futureId;
        this.SessionId = id;
    }
}
