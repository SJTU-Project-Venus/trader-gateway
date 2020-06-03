package com.sjtu.trade.service;

import com.sjtu.trade.dto.LoginRequest;

public interface TraderUserService {
    boolean Login(LoginRequest loginRequest);
    boolean Register(LoginRequest loginRequest);
}
