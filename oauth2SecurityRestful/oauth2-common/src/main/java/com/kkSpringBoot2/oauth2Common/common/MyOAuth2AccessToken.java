package com.kkSpringBoot2.oauth2Common.common;

import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;
import java.util.*;

/**
 * description: 自定义OAuth2AccessToken
 * author: ckk
 * create: 2019-04-26 12:42
 */

public class MyOAuth2AccessToken implements Serializable, OAuth2AccessToken {

    private static final long serialVersionUID = 914967629530462926L;

    private MyOauth2AccessTokenEntity tokenEntity = new MyOauth2AccessTokenEntity();


    /**
     * Create an access token from the value provided.
     */
    public MyOAuth2AccessToken(String value) {
        this.tokenEntity.setTokenType(BEARER_TYPE.toLowerCase());
        this.tokenEntity.setValue(value);
    }

    /**
     * Private constructor for JPA and other serialization tools.
     */
    @SuppressWarnings("unused")
    private MyOAuth2AccessToken() {
        this((String) null);
    }

    /**
     * Copy constructor for access token.
     *
     * @param accessToken
     */
    public MyOAuth2AccessToken(OAuth2AccessToken accessToken) {
        this(accessToken.getValue());
        setAdditionalInformation(accessToken.getAdditionalInformation());
        setRefreshToken(accessToken.getRefreshToken());
        setExpiration(accessToken.getExpiration());
        setScope(accessToken.getScope());
        setTokenType(accessToken.getTokenType());
        this.tokenEntity.setTokenType(BEARER_TYPE.toLowerCase());
    }

    public void setValue(String value) {
        this.tokenEntity.setValue(value);
    }

    /**
     * The token value.
     *
     * @return The token value.
     */
    public String getValue() {
        return this.tokenEntity.getValue();
    }

    public int getExpiresIn() {
        return this.tokenEntity.getExpiration() != null ? Long.valueOf((this.tokenEntity.getExpiration().getTime() - System.currentTimeMillis()) / 1000L)
                .intValue() : 0;
    }

    protected void setExpiresIn(int delta) {
        setExpiration(new Date(System.currentTimeMillis() + delta));
    }

    /**
     * The instant the token expires.
     *
     * @return The instant the token expires.
     */
    public Date getExpiration() {
        return this.tokenEntity.getExpiration();
    }

    /**
     * The instant the token expires.
     *
     * @param expiration The instant the token expires.
     */
    public void setExpiration(Date expiration) {
        this.tokenEntity.setExpiration(expiration);
    }

    /**
     * Convenience method for checking expiration
     *
     * @return true if the expiration is befor ethe current time
     */
    public boolean isExpired() {
        return this.tokenEntity.getExpiration() != null && this.tokenEntity.getExpiration().before(new Date());
    }

    /**
     * The token type, as introduced in draft 11 of the OAuth 2 spec. The spec doesn't define (yet) that the valid token
     * types are, but says it's required so the default will just be "undefined".
     *
     * @return The token type, as introduced in draft 11 of the OAuth 2 spec.
     */
    public String getTokenType() {
        return this.tokenEntity.getTokenType();
    }

    /**
     * The token type, as introduced in draft 11 of the OAuth 2 spec.
     *
     * @param tokenType The token type, as introduced in draft 11 of the OAuth 2 spec.
     */
    public void setTokenType(String tokenType) {
        this.tokenEntity.setTokenType(tokenType);
    }

    /**
     * The refresh token associated with the access token, if any.
     *
     * @return The refresh token associated with the access token, if any.
     */
    public OAuth2RefreshToken getRefreshToken() {
        return this.tokenEntity.getRefreshToken();
    }

    /**
     * The refresh token associated with the access token, if any.
     *
     * @param refreshToken The refresh token associated with the access token, if any.
     */
    public void setRefreshToken(OAuth2RefreshToken refreshToken) {
        this.tokenEntity.setRefreshToken(refreshToken);
    }

    /**
     * The scope of the token.
     *
     * @return The scope of the token.
     */
    public Set<String> getScope() {
        return this.tokenEntity.getScope();
    }

    /**
     * The scope of the token.
     *
     * @param scope The scope of the token.
     */
    public void setScope(Set<String> scope) {
        this.tokenEntity.setScope(scope);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    public static OAuth2AccessToken valueOf(Map<String, String> tokenParams) {
        MyOAuth2AccessToken token = new MyOAuth2AccessToken(tokenParams.get(ACCESS_TOKEN));

        if (tokenParams.containsKey(EXPIRES_IN)) {
            long expiration = 0;
            try {
                expiration = Long.parseLong(String.valueOf(tokenParams.get(EXPIRES_IN)));
            }
            catch (NumberFormatException e) {
                // fall through...
            }
            token.setExpiration(new Date(System.currentTimeMillis() + (expiration * 1000L)));
        }

        if (tokenParams.containsKey(REFRESH_TOKEN)) {
            String refresh = tokenParams.get(REFRESH_TOKEN);
            DefaultOAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(refresh);
            token.setRefreshToken(refreshToken);
        }

        if (tokenParams.containsKey(SCOPE)) {
            Set<String> scope = new TreeSet<String>();
            for (StringTokenizer tokenizer = new StringTokenizer(tokenParams.get(SCOPE), " ,"); tokenizer
                    .hasMoreTokens();) {
                scope.add(tokenizer.nextToken());
            }
            token.setScope(scope);
        }

        if (tokenParams.containsKey(TOKEN_TYPE)) {
            token.setTokenType(tokenParams.get(TOKEN_TYPE));
        }

        return token;
    }

    /**
     * Additional information that token granters would like to add to the token, e.g. to support new token types.
     *
     * @return the additional information (default empty)
     */
    public Map<String, Object> getAdditionalInformation() {
        return this.tokenEntity.getAdditionalInformation();
    }

    /**
     * Additional information that token granters would like to add to the token, e.g. to support new token types. If
     * the values in the map are primitive then remote communication is going to always work. It should also be safe to
     * use maps (nested if desired), or something that is explicitly serializable by Jackson.
     *
     * @param additionalInformation the additional information to set
     */
    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.tokenEntity.setAdditionalInformation( new LinkedHashMap<String, Object>(additionalInformation));
    }

    public String getMy_value() {
        return this.tokenEntity.getMy_value();
    }

    public void setMy_value(String my_value) {
        this.tokenEntity.setMy_value(my_value);
    }
}
