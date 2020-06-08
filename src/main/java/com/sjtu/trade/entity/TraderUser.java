package com.sjtu.trade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class TraderUser {
    @Id
    private String phone; // phone
    private String password;
    private String traderCompony;

    public String getTraderCompony() {
        return traderCompony;
    }

    public void setTraderCompony(String traderCompony) {
        this.traderCompony = traderCompony;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
