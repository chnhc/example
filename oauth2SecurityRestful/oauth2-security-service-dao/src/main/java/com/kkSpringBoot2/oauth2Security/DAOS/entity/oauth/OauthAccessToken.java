package com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth;

import lombok.Data;

/**
 * description: token
 * author: ckk
 * create: 2019-04-05 18:52
 */
@Data
public class OauthAccessToken  {

    private String token_id;
    private byte[] token;
    private String authentication_id;
    private String user_name;
    private String client_id;
    private String refresh_token;
    private byte[] authentication;

}
