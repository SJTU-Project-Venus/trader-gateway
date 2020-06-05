package com.sjtu.trade.exception.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomizeException extends RuntimeException {
  private HttpStatus code;
  public CustomizeException(String message, HttpStatus code) {
    super(message);
    this.code = code;
  }

  public HttpStatus getCode() {
    return code;
  }

  public void setCode(HttpStatus code) {
    this.code = code;
  }
}
