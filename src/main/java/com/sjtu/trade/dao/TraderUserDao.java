package com.sjtu.trade.dao;

import com.sjtu.trade.entity.Trader;
import com.sjtu.trade.entity.TraderUser;

public interface TraderUserDao {
    TraderUser OneTrader(String id);
    boolean CreateTraderUser(TraderUser traderUser);
}
