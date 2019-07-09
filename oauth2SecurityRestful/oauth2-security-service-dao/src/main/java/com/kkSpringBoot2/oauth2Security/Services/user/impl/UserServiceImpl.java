package com.kkSpringBoot2.oauth2Security.Services.user.impl;


import com.kkSpringBoot2.oauth2Security.DAOS.dao.user.UserDAO;
import com.kkSpringBoot2.oauth2Security.DAOS.entity.user.UserEntity;
import com.kkSpringBoot2.oauth2Security.Services.user.UserService;
import com.kkSpringBoot2.oauth2Security.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * description: 用户操作
 * author: ckk
 * create: 2019-04-20 08:24
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void updateAdmin(UserEntity userEntity) {
        userEntity.setUser_update_time(DateUtils.getSecondTimestamp(new Date()));
        userDAO.updateAdmin(userEntity);
    }

    @Override
    public boolean isExistAdminByUserId(String user_id) {
        UserEntity userEntity =userDAO.selectAdminByUserId(user_id);
        if(userEntity == null || userEntity.getUser_pwd() == null || StringUtils.isEmpty(userEntity.getUser_pwd())){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public UserEntity selectAdmin() {
        return userDAO.selectAdmin();
    }
}
