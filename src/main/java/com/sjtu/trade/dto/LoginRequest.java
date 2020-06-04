package com.sjtu.trade.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest extends Request implements Serializable {
  private String password;
  private Long phone;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }
}
