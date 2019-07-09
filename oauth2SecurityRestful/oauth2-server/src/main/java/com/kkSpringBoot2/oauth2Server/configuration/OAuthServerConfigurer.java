package com.kkSpringBoot2.oauth2Server.configuration;

import com.kkSpringBoot2.oauth2Server.filter.BootBasicAuthenticationFilter;
import com.kkSpringBoot2.oauth2Common.provider.token.MyTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Arrays;

/**
 * 认证服务器
 */
@Configuration
@EnableAuthorizationServer
@Order(7)
public class OAuthServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private WebResponseExceptionTranslator bootWebResponseExceptionTranslator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BootBasicAuthenticationFilter filter;

    @Autowired
    private TokenStore tokenStore;


    private AccessTokenConverter accessTokenConverter;


    private TokenEnhancer tokenEnhancer;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired(required = false)
    private JwtAccessTokenConverter converter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //System.out.println(passwordEncoder.encode("123123"));
        clients
                .inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("{noop}admin"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("all").accessTokenValiditySeconds(20).refreshTokenValiditySeconds(2592000)
                .redirectUris("https://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //.accessTokenConverter(accessTokenConverter())
                //.tokenEnhancer(tokenEnhancer())
                .tokenServices(defaultTokenServices())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // 处理 ExceptionTranslationFilter 抛出的异常
                .exceptionTranslator(bootWebResponseExceptionTranslator)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 数据库保存token;
                //.tokenStore(tokenStore)
                ;




    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.addTokenEndpointAuthenticationFilter(filter);
        oauthServer
                .authenticationEntryPoint(authenticationEntryPoint)
                .tokenKeyAccess("permitAll()") //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .checkTokenAccess("isAuthenticated()");//url:/oauth/check_token allow check token

    }



    @Bean
    public MyTokenServices defaultTokenServices() {

     /*   DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(reuseRefreshToken);

        tokenServices.setTokenEnhancer(tokenEnhancer());
        addUserDetailsService(tokenServices, this.userDetailsService);*/

        final MyTokenServices defaultTokenServices = new MyTokenServices();
        defaultTokenServices.setAuthenticationManager(authenticationManager);

        //defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        //if (converter != null) defaultTokenServices.setTokenEnhancer(converter);
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(true);
        //defaultTokenServices.setClientDetailsService(clientDetailsService);
        //defaultTokenServices.setTokenEnhancer(tokenEnhancer());
        addUserDetailsService(defaultTokenServices, this.userDetailsService);
        return defaultTokenServices;
    }

    private void addUserDetailsService(DefaultTokenServices tokenServices, UserDetailsService userDetailsService) {
        if (userDetailsService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(
                    userDetailsService));
            tokenServices
                    .setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider> asList(provider)));
        }
    }


    public TokenEnhancer tokenEnhancer() {


        if (this.tokenEnhancer == null && accessTokenConverter() instanceof JwtAccessTokenConverter ) {
            tokenEnhancer = (TokenEnhancer) accessTokenConverter;
        }
        return this.tokenEnhancer;
    }


    public AccessTokenConverter accessTokenConverter() {
        if (this.accessTokenConverter == null) {
            accessTokenConverter = new DefaultAccessTokenConverter();
        }
        return this.accessTokenConverter;
    }

}
