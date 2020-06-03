package com.sjtu.trade.exception.exceptions;

import java.util.ArrayList;
import java.util.List;

public class RequestValidationException extends RuntimeException {
  private List<String> errors;

  public RequestValidationException(List<String> errors) {
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }
}
