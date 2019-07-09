package com.kkSpringBoot2.oauth2Security.Services.oauth.impl;

import com.kkSpringBoot2.oauth2Security.DAOS.dao.oauth.OauthAccessTokenDAO;
import com.kkSpringBoot2.oauth2Security.DAOS.dao.oauth.OauthRefreshTokenDAO;
import com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth.OauthAccessToken;
import com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth.OauthRefreshToken;
import com.kkSpringBoot2.oauth2Security.Services.oauth.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: oauth服务
 * author: ckk
 * create: 2019-04-05 20:03
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private OauthAccessTokenDAO oauthAccessTokenDAO;

    @Autowired
    private OauthRefreshTokenDAO oauthRefreshTokenDAO;


    @Override
    public OauthAccessToken selectOauthAccessToken(String token_id) {
        return oauthAccessTokenDAO.selectOauthAccessToken(token_id);
    }

    @Override
    public OauthAccessToken getTokenByAuthenticationId(String authentication_id) {
        return oauthAccessTokenDAO.getTokenByAuthenticationId(authentication_id);
    }

    @Override
    public void insertAccessToken(OauthAccessToken oauthAccessToken) {
        oauthAccessTokenDAO.insertAccessToken(oauthAccessToken);
    }

    @Override
    public OauthAccessToken selectAccessToken(String token_id) {
        return oauthAccessTokenDAO.selectAccessToken(token_id);
    }

    @Override
    public OauthAccessToken selectAccessTokenAuthentication(String token_id) {
        return oauthAccessTokenDAO.selectAccessTokenAuthentication(token_id);
    }

    @Override
    public List<OauthAccessToken> selectAccessTokensFromClientId(String client_id) {
        return oauthAccessTokenDAO.selectAccessTokensFromClientId(client_id);
    }

    @Override
    public List<OauthAccessToken> selectAccessTokensFromUserName(String user_name) {
        return oauthAccessTokenDAO.selectAccessTokensFromUserName(user_name);
    }

    @Override
    public List<OauthAccessToken> selectAccessTokensFromUserNameAndClientId(String user_name, String client_id) {
        return oauthAccessTokenDAO.selectAccessTokensFromUserNameAndClientId(user_name, client_id);
    }


    @Override
    public void deleteAccessTokenSql(String token_id) {
        oauthAccessTokenDAO.deleteAccessToken(token_id);
    }

    @Override
    public void insertRefreshToken(OauthRefreshToken oauthRefreshToken) {
        oauthRefreshTokenDAO.insertRefreshToken(oauthRefreshToken);
    }

    @Override
    public OauthRefreshToken selectRefreshToken(String token_id) {
        return oauthRefreshTokenDAO.selectRefreshToken(token_id);
    }

    @Override
    public void deleteRefreshToken(String token_id) {
        oauthRefreshTokenDAO.deleteRefreshToken(token_id);
    }

    @Override
    public OauthRefreshToken selectRefreshTokenAuthentication(String token_id) {
        return oauthRefreshTokenDAO.selectRefreshTokenAuthentication(token_id);
    }

    @Override
    public void deleteAccessTokenFromRefreshToken(String refresh_token) {
        oauthAccessTokenDAO.deleteAccessTokenFromRefreshToken(refresh_token);
    }

    @Override
    public void deleteRefreshTokenByAccessToken(String token_id) {
        oauthRefreshTokenDAO.deleteRefreshTokenByAccessToken(token_id);
    }

    @Override
    public String readRefreshTokenValueByAccessToken(String token_id) {
        return oauthRefreshTokenDAO.readRefreshTokenValueByAccessToken(token_id);
    }


}
