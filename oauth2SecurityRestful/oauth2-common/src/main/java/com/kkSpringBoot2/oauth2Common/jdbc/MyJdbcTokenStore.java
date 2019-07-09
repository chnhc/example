package com.kkSpringBoot2.oauth2Common.jdbc;


import com.kkSpringBoot2.oauth2Common.provider.token.EMyTokenStore;
import com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth.OauthAccessToken;
import com.kkSpringBoot2.oauth2Security.DAOS.entity.oauth.OauthRefreshToken;
import com.kkSpringBoot2.oauth2Security.Services.oauth.OauthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * description: aaa
 * author: ckk
 * create: 2019-04-05 20:46
 */
public class MyJdbcTokenStore extends EMyTokenStore {

    private static final Log LOG = LogFactory.getLog(MyJdbcTokenStore.class);

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Autowired
    private OauthService oauthService;

    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }

    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;

        String key = authenticationKeyGenerator.extractKey(authentication);
        try {
           /* accessToken = jdbcTemplate.queryForObject(selectAccessTokenFromAuthenticationSql,
                    new RowMapper<OAuth2AccessToken>() {
                        public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return deserializeAccessToken(rs.getBytes(2));
                        }
                    }, key);*/
            OauthAccessToken oauthAccessToken= oauthService.getTokenByAuthenticationId(key);
            if(oauthAccessToken != null )
               accessToken =   deserializeAccessToken(oauthAccessToken.getToken());


        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to find access token for authentication " + authentication);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.error("Could not extract access token for authentication " + authentication, e);
        }

        if (accessToken != null
                && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            removeAccessToken(accessToken.getValue());
            // Keep the store consistent (maybe the same user is represented by this authentication but the details have
            // changed)
            storeAccessToken(accessToken, authentication);
        }
        return accessToken;
    }

    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }

        if (readAccessToken(token.getValue())!=null) {
            removeAccessToken(token.getValue());
        }

       /*
       token_id, token, authentication_id, user_name, client_id, authentication, refresh_token
       jdbcTemplate.update(insertAccessTokenSql, new Object[] { extractTokenKey(token.getValue()),
                new SqlLobValue(serializeAccessToken(token)), authenticationKeyGenerator.extractKey(authentication),
                authentication.isClientOnly() ? null : authentication.getName(),
                authentication.getOAuth2Request().getClientId(),
                new SqlLobValue(serializeAuthentication(authentication)), extractTokenKey(refreshToken) }, new int[] {
                Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR });
         */

        OauthAccessToken oauthAccessToken = new OauthAccessToken();
        oauthAccessToken.setAuthentication(serializeAuthentication(authentication));
        oauthAccessToken.setAuthentication_id(authenticationKeyGenerator.extractKey(authentication));
        oauthAccessToken.setClient_id(authentication.getOAuth2Request().getClientId());
        oauthAccessToken.setRefresh_token(extractTokenKey(refreshToken));
        oauthAccessToken.setToken(serializeAccessToken(token));
        oauthAccessToken.setToken_id(extractTokenKey(token.getValue()));
        oauthAccessToken.setUser_name( authentication.isClientOnly() ? null : authentication.getName());

        oauthService.insertAccessToken(oauthAccessToken);
    }

    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        try {
          /*  accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
                public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return deserializeAccessToken(rs.getBytes(2));
                }
            }, extractTokenKey(tokenValue));*/
            OauthAccessToken oauthAccessToken= oauthService.selectAccessToken( extractTokenKey(tokenValue));
            if(oauthAccessToken != null )
            accessToken = deserializeAccessToken(oauthAccessToken.getToken());
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + tokenValue);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " + tokenValue, e);
            removeAccessToken(tokenValue);
        }

        return accessToken;
    }

    public void removeAccessToken(OAuth2AccessToken token) {
        removeAccessToken(token.getValue());
    }

    public void removeAccessToken(String tokenValue) {
        //jdbcTemplate.update(deleteAccessTokenSql, extractTokenKey(tokenValue));
        oauthService.deleteAccessTokenSql(extractTokenKey(tokenValue));
    }

    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    public OAuth2Authentication readAuthentication(String token) {
        OAuth2Authentication authentication = null;

        try {
            /*authentication = jdbcTemplate.queryForObject(selectAccessTokenAuthenticationSql,
                    new RowMapper<OAuth2Authentication>() {
                        public OAuth2Authentication mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return deserializeAuthentication(rs.getBytes(2));
                        }
                    }, extractTokenKey(token));*/
            OauthAccessToken oauthAccessToken= oauthService.selectAccessTokenAuthentication(extractTokenKey(token));
            if(oauthAccessToken != null )
            authentication = deserializeAuthentication(oauthAccessToken.getAuthentication());
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + token);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize authentication for " + token, e);
            removeAccessToken(token);
        }

        return authentication;
    }

    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
       /* jdbcTemplate.update(insertRefreshTokenSql, new Object[] { extractTokenKey(refreshToken.getValue()),
                new SqlLobValue(serializeRefreshToken(refreshToken)),
                new SqlLobValue(serializeAuthentication(authentication)) }, new int[] { Types.VARCHAR, Types.BLOB,
                Types.BLOB });*/
        OauthRefreshToken oauthRefreshToken = new OauthRefreshToken();
        oauthRefreshToken.setToken_id(extractTokenKey(refreshToken.getValue()));
        oauthRefreshToken.setToken(serializeRefreshToken(refreshToken));
        oauthRefreshToken.setAuthentication(serializeAuthentication(authentication));

        oauthService.insertRefreshToken(oauthRefreshToken);
    }

    public OAuth2RefreshToken readRefreshToken(String token) {
        OAuth2RefreshToken refreshToken = null;

        try {
           /* refreshToken = jdbcTemplate.queryForObject(selectRefreshTokenSql, new RowMapper<OAuth2RefreshToken>() {
                public OAuth2RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return deserializeRefreshToken(rs.getBytes(2));
                }
            }, extractTokenKey(token));*/
            OauthRefreshToken oauthRefreshToken= oauthService.selectRefreshToken(extractTokenKey(token));
            if(oauthRefreshToken != null )
                refreshToken = deserializeRefreshToken(oauthRefreshToken.getToken());
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find refresh token for token " + token);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize refresh token for token " + token, e);
            removeRefreshToken(token);
        }

        return refreshToken;
    }

    public void removeRefreshToken(OAuth2RefreshToken token) {
        removeRefreshToken(token.getValue());
    }

    public void removeRefreshToken(String token) {
       // jdbcTemplate.update(deleteRefreshTokenSql, extractTokenKey(token));
        oauthService.deleteRefreshToken(extractTokenKey(token));
    }

    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return readAuthenticationForRefreshToken(token.getValue());
    }

    public OAuth2Authentication readAuthenticationForRefreshToken(String value) {
        OAuth2Authentication authentication = null;

        try {
            /*authentication = jdbcTemplate.queryForObject(selectRefreshTokenAuthenticationSql,
                    new RowMapper<OAuth2Authentication>() {
                        public OAuth2Authentication mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return deserializeAuthentication(rs.getBytes(2));
                        }
                    }, extractTokenKey(value));*/
            OauthRefreshToken oauthRefreshToken= oauthService.selectRefreshTokenAuthentication(extractTokenKey(value));
            if(oauthRefreshToken != null )
                authentication = deserializeAuthentication(oauthRefreshToken.getAuthentication());
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + value);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " + value, e);
            removeRefreshToken(value);
        }

        return authentication;
    }

    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        removeAccessTokenUsingRefreshToken(refreshToken.getValue());
    }

    public void removeAccessTokenUsingRefreshToken(String refreshToken) {
        /*jdbcTemplate.update(deleteAccessTokenFromRefreshTokenSql, new Object[] { extractTokenKey(refreshToken) },
                new int[] { Types.VARCHAR });*/
        oauthService.deleteAccessTokenFromRefreshToken(extractTokenKey(refreshToken));
    }

    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();

        try {
          /*  accessTokens = jdbcTemplate.query(selectAccessTokensFromClientIdSql, new JdbcTokenStore.SafeAccessTokenRowMapper(),
                    clientId);*/
            List<OauthAccessToken> oauthAccessTokenList= oauthService.selectAccessTokensFromClientId(clientId);
            if(oauthAccessTokenList != null && oauthAccessTokenList.size()>0) {
                for (OauthAccessToken o : oauthAccessTokenList) {
                    accessTokens.add(deserializeAccessToken(o.getToken()));
                }
            }
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for clientId " + clientId);
            }
        }
        accessTokens = removeNulls(accessTokens);

        return accessTokens;
    }

    public Collection<OAuth2AccessToken> findTokensByUserName(String userName) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();

        try {
           /* accessTokens = jdbcTemplate.query(selectAccessTokensFromUserNameSql, new JdbcTokenStore.SafeAccessTokenRowMapper(),
                    userName);*/

            List<OauthAccessToken> oauthAccessTokenList= oauthService.selectAccessTokensFromUserName(userName);
            if(oauthAccessTokenList != null && oauthAccessTokenList.size()>0) {
                for (OauthAccessToken o : oauthAccessTokenList) {
                    accessTokens.add(deserializeAccessToken(o.getToken()));
                }
            }

        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled())
                LOG.info("Failed to find access token for userName " + userName);
        }
        accessTokens = removeNulls(accessTokens);

        return accessTokens;
    }

    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();

        try {
            /*accessTokens = jdbcTemplate.query(selectAccessTokensFromUserNameAndClientIdSql, new JdbcTokenStore.SafeAccessTokenRowMapper(),
                    userName, clientId);*/
            List<OauthAccessToken> oauthAccessTokenList= oauthService.selectAccessTokensFromUserNameAndClientId(userName,clientId);
            if(oauthAccessTokenList != null && oauthAccessTokenList.size()>0) {
                for (OauthAccessToken o : oauthAccessTokenList) {
                    accessTokens.add(deserializeAccessToken(o.getToken()));
                }
            }

        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for clientId " + clientId + " and userName " + userName);
            }
        }
        accessTokens = removeNulls(accessTokens);

        return accessTokens;
    }

    private List<OAuth2AccessToken> removeNulls(List<OAuth2AccessToken> accessTokens) {
        List<OAuth2AccessToken> tokens = new ArrayList<OAuth2AccessToken>();
        for (OAuth2AccessToken token : accessTokens) {
            if (token != null) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    protected String extractTokenKey(String value) {
        if (value == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }

        try {
            byte[] bytes = digest.digest(value.getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    @Override
    public void removeRefreshTokenByAccessToken(OAuth2AccessToken token) {
        oauthService.deleteRefreshTokenByAccessToken(extractTokenKey(token.getValue()));
    }

    @Override
    public String readRefreshTokenValueByAccessToken(String tokenValue) {
        return oauthService.readRefreshTokenValueByAccessToken(extractTokenKey(tokenValue));
    }

    @Override
    public OAuth2RefreshToken readRefreshTokenByTokenID(String token_id) {

        OAuth2RefreshToken refreshToken = null;

        try {
           /* refreshToken = jdbcTemplate.queryForObject(selectRefreshTokenSql, new RowMapper<OAuth2RefreshToken>() {
                public OAuth2RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return deserializeRefreshToken(rs.getBytes(2));
                }
            }, extractTokenKey(token));*/
            OauthRefreshToken oauthRefreshToken= oauthService.selectRefreshToken(token_id);
            if(oauthRefreshToken != null )
                refreshToken = deserializeRefreshToken(oauthRefreshToken.getToken());
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find refresh token for token " + token_id);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize refresh token for token " + token_id, e);
            removeRefreshToken(token_id);
        }

        return refreshToken;
    }

    private final class SafeAccessTokenRowMapper implements RowMapper<OAuth2AccessToken> {
        public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return deserializeAccessToken(rs.getBytes(2));
            }
            catch (IllegalArgumentException e) {
                String token = rs.getString(1);
                //jdbcTemplate.update(deleteAccessTokenSql, token);
                return null;
            }
        }
    }

    protected byte[] serializeAccessToken(OAuth2AccessToken token) {
        return SerializationUtils.serialize(token);
    }

    protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
        return SerializationUtils.serialize(token);
    }

    protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
        return SerializationUtils.serialize(authentication);
    }

    protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
        return SerializationUtils.deserialize(token);
    }

    protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
        return SerializationUtils.deserialize(token);
    }

    protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
        return SerializationUtils.deserialize(authentication);
    }

}
