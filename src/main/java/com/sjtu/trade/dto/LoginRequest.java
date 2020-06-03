package com.sjtu.trade.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest extends Request implements Serializable {
  private String password;
  private String traderName;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTraderName() {
    return traderName;
  }

  public void setTraderName(String traderName) {
    this.traderName = traderName;
  }
}
