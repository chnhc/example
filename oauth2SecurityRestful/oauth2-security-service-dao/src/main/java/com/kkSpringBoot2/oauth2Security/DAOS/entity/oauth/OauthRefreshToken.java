package com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth;

import lombok.Data;

/**
 * description: 刷新token
 * author: ckk
 * create: 2019-04-06 17:57
 */
@Data
public class OauthRefreshToken {

    private String token_id;
    private byte[] token;
    private byte[] authentication;

}
