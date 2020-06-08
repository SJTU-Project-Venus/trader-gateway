package com.sjtu.trade.controller;

import com.sjtu.trade.dto.JWTResponse;
import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.entity.TraderUser;
import com.sjtu.trade.service.TraderUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traderUser")
@Api("Trader 用户登录注册相关操作")
public class TraderUserController {

  @Autowired
  private TraderUserService traderUserService;

  @RequestMapping(value = "/register", method = { RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> exceptionURL(@RequestBody TraderUser traderUser) {
    return ResponseEntity.status(HttpStatus.OK).body(traderUserService.Register(traderUser));
  }
}
