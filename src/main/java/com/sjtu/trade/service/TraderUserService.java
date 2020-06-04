package com.sjtu.trade.service;

import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.entity.TraderUser;

public interface TraderUserService {
    boolean Login(LoginRequest loginRequest);
    boolean Register(TraderUser traderUser);
}
