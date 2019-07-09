package com.kkSpringBoot2.oauth2Security.DAOS.dao.user;

import com.kkSpringBoot2.oauth2Security.DAOS.entity.user.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * description: 用户操作
 * author: ckk
 * create: 2019-04-20 08:15
 */
@Repository(value = "userDAO")
public interface UserDAO {

    void updateAdmin(UserEntity userEntity);

    UserEntity selectAdminByUserId(String user_id);

    UserEntity selectAdmin();

}
