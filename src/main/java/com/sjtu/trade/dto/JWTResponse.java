package com.sjtu.trade.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class JWTResponse implements Serializable {
  JWTResponse(List<Integer> l) {

  }
  private String jwt;
}
