package com.sjtu.trade.exception;

import java.util.HashMap;
import java.util.Map;

import com.sjtu.trade.exception.exceptions.CustomizeException;
import com.sjtu.trade.exception.exceptions.RequestValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

  @ExceptionHandler(value = {CustomizeException.class})
  public ResponseEntity<?> customizeExceptionHandle(CustomizeException e) {

    Map<String, Object> m = new HashMap<>();
    m.put("msg", e.getMessage());
    return new ResponseEntity<>(m, e.getCode());
  }

  @ExceptionHandler(value = {RequestValidationException.class})
  public ResponseEntity<?> requestValidationExceptionHandle(RequestValidationException e) {
    return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
  }

}
