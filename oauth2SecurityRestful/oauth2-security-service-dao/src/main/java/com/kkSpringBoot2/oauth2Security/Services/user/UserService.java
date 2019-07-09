package com.kkSpringBoot2.oauth2Security.Services.user;


import com.kkSpringBoot2.oauth2Security.DAOS.entity.user.UserEntity;

public interface UserService {

    void updateAdmin(UserEntity userEntity);

    boolean isExistAdminByUserId(String user_id);

    UserEntity selectAdmin();
}
