package com.kkSpringBoot2.oauth2Security.DAOS.entity.user;

import lombok.Data;

/**
 * description: 用户
 * author: ckk
 * create: 2019-04-20 08:18
 */
@Data
public class UserEntity {

    private int id;
    private String user_id;
    private String type;
    private String user_name;
    private String user_pwd;
    private int status;
    private long user_update_time;
    private long user_create_time;
}
