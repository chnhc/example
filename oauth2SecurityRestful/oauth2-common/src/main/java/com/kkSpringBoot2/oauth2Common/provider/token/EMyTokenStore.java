package com.kkSpringBoot2.oauth2Common.provider.token;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * description: 自定义操作Oauth2 token
 * author: ckk
 * create: 2019-04-27 08:28
 */
public abstract class EMyTokenStore implements TokenStore {

    /**
     * Remove a refresh token by AccessToken from the store.
     *
     * @param token The token to remove from the store.
     */
    public abstract void removeRefreshTokenByAccessToken(OAuth2AccessToken token);

    /**
     * Read refresh token_id by tokenValue from the store
     *
     * @param tokenValue The token to remove from the store.
     */
    public abstract String readRefreshTokenValueByAccessToken(String tokenValue);

    public abstract OAuth2RefreshToken readRefreshTokenByTokenID(String token_id);

}
