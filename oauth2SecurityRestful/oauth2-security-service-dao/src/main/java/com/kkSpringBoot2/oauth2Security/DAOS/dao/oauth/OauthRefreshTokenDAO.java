package com.kkSpringBoot2.oauth2Security.DAOS.dao.oauth;

import com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth.OauthRefreshToken;
import org.springframework.stereotype.Repository;

@Repository(value = "oauthRefreshTokenDAO")
public interface OauthRefreshTokenDAO {

    void insertRefreshToken(OauthRefreshToken oauthRefreshToken);

    OauthRefreshToken selectRefreshToken(String token_id);

    OauthRefreshToken selectRefreshTokenAuthentication(String token_id);

    void deleteRefreshToken(String token_id);

    void deleteRefreshTokenByAccessToken(String token_id);

    String readRefreshTokenValueByAccessToken(String token_id);

}
