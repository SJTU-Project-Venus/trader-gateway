package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.TraderDao;
import com.sjtu.trade.entity.Trader;
import com.sjtu.trade.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraderDaoImpl implements TraderDao {

    @Autowired
    private TraderRepository traderRepository;
    @Override
    public Trader OneTrader(Long id){
        return traderRepository.findById(id).get();
    }
}
