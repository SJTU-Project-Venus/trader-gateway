package com.sjtu.trade.config;

import com.sjtu.trade.entity.TraderUser;
import com.sjtu.trade.entity.UserDetailVo;
import com.sjtu.trade.repository.TraderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("UserDetailsService")
public class MyCustomerService implements UserDetailsService {

    @Autowired
    private TraderUserRepository traderUserRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            TraderUser userVo = traderUserRepository.findByPhone(username);
            if (userVo==null) {
                throw new UsernameNotFoundException("用户不存在或密码错误");
            }
            UserDetailVo userVoDetail = new UserDetailVo();
            userVoDetail.setUserId(userVo.getPhone());
            userVoDetail.setUsername(userVo.getPhone());
            userVoDetail.setPassword(userVo.getPassword());
            return userVoDetail;
        }
    }
