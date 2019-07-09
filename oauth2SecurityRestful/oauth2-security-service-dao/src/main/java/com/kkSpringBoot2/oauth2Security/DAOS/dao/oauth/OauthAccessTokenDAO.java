package com.kkSpringBoot2.oauth2Security.DAOS.dao.oauth;

import com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth.OauthAccessToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: token
 * author: ckk
 * create: 2019-04-05 18:52
 */
@Repository(value = "oauthAccessTokenDAO")
public interface OauthAccessTokenDAO {

    OauthAccessToken selectOauthAccessToken(String token_id);

    OauthAccessToken selectAccessToken(String token_id);

    OauthAccessToken selectAccessTokenAuthentication(String token_id);

    OauthAccessToken getTokenByAuthenticationId(String authentication_id);

    List<OauthAccessToken> selectAccessTokensFromClientId(String client_id);

    List<OauthAccessToken> selectAccessTokensFromUserName(String user_name);

    List<OauthAccessToken> selectAccessTokensFromUserNameAndClientId(@Param("user_name") String user_name, @Param("client_id") String client_id);

    void insertAccessToken(OauthAccessToken oauthAccessToken);

    void deleteAccessToken(String token_id);

    void deleteAccessTokenFromRefreshToken(String refresh_token);


}
