package com.kkSpringBoot2.oauth2resource.provider.expression;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * 鉴权服务
 * // 往ExpressionHandler中设置ApplicationContext，否则在鉴权时无法通过@调用CustomSecurityExpression，
 *   比如：@cse.permitAll(authentication, 'customSecurityExpression')
 *
 */
@Configuration("appSecurityExpressionHandler")
public class AppSecurityExpressionHandler extends OAuth2WebSecurityExpressionHandler {

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
}

