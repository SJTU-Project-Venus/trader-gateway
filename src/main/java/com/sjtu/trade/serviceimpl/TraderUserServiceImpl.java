package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.dao.TraderDao;
import com.sjtu.trade.dao.TraderUserDao;
import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.entity.TraderUser;
import com.sjtu.trade.repository.TraderRepository;
import com.sjtu.trade.service.TraderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderUserServiceImpl implements TraderUserService {
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private TraderUserDao traderUserDao;
    @Override
    public boolean Login(LoginRequest loginRequest){
        TraderUser tempUser = traderUserDao.OneTrader(loginRequest.getPhone());
        if(tempUser != null){
            return tempUser.getPassword().equals(loginRequest.getPassword());
        }
        else
            return false;
    }
    @Override
    public boolean Register(TraderUser traderUser){
        if(traderDao.OneTrader(traderUser.getTraderId())!=null && traderUserDao.OneTrader(traderUser.getId()) == null){
            return traderUserDao.CreateTraderUser(traderUser);
        }
        else
            return false;
    }
}
