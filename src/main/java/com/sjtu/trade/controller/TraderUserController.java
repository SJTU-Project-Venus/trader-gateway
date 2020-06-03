package com.sjtu.trade.controller;

import com.sjtu.trade.dto.JWTResponse;
import com.sjtu.trade.dto.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sjtu.trade.exception.exceptions.CustomizeException;

@RestController
@RequestMapping("/traderUser")
@Api("Trader 用户登录注册相关操作")
public class TraderUserController {
  @ApiOperation(value = "用户登录", notes = "通过用户账号密码进行登录")
  @RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    return null;
  }

  @RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> exceptionURL(@RequestBody LoginRequest loginRequest) {
    throw new CustomizeException("error", HttpStatus.FORBIDDEN);
  }

  @ApiOperation(value = "进行审查或进行决议", notes = "债权人行为或律师行为")
  @ApiResponses({
      @ApiResponse(code = 200, message = "ok", response = JWTResponse.class)
  })
  @RequestMapping(value = "/annotationExample", method = { RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> annotationExample(@RequestHeader("jwt") String jwt) {
    throw new CustomizeException("error", HttpStatus.FORBIDDEN);
  }
}
