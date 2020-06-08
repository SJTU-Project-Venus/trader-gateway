package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.TraderUserDao;
import com.sjtu.trade.entity.TraderUser;
import com.sjtu.trade.repository.TraderUserRepository;
import com.sjtu.trade.service.IDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraderUserDaoImpl implements TraderUserDao {

    @Autowired
    private TraderUserRepository traderUserRepository;

    @Autowired
    private IDService idService;
    @Override
    public TraderUser OneTrader(String id){
        return traderUserRepository.findByPhone(id);
    }
    @Override
    public boolean CreateTraderUser(TraderUser traderUser){
        //traderUser.setId(idService.generate("traderUser"));
        traderUserRepository.save(traderUser);
        return true;
    }
}
