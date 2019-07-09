package com.kkSpringBoot2.oauth2Common.common;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * description: 自定义token
 * author: ckk
 * create: 2019-04-27 07:00
 */
public class MyOauth2AccessTokenEntity implements Serializable {

    private String value;

    private String my_value;

    private Date expiration;

    private String tokenType ;

    private OAuth2RefreshToken refreshToken;

    private Set<String> scope;

    private Map<String, Object> additionalInformation = Collections.emptyMap();;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMy_value() {
        return my_value;
    }

    public void setMy_value(String my_value) {
        this.my_value = my_value;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public OAuth2RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(OAuth2RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
