package com.kkSpringBoot2.oauth2resource.configuration;

import com.kkSpringBoot2.oauth2Common.provider.token.MyTokenServices;
import com.kkSpringBoot2.oauth2resource.access.AppAccessDeniedHandler;
import com.kkSpringBoot2.oauth2resource.filter.JWTAuthenticationFilter;
import com.kkSpringBoot2.oauth2resource.provider.error.BootOAuth2AuthExceptionEntryPoint;
import com.kkSpringBoot2.oauth2resource.provider.expression.AppSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * description: 资源服务器
 * author: ckk
 * create: 2019-03-31 13:27
 */

@Configuration
@EnableResourceServer
public class OAuthResourceConfigurer extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "*";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private MyTokenServices defaultTokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

  /*  @Autowired
    private PersonalProperties personalProperties;*/

    @Autowired
    private AppSecurityExpressionHandler appSecurityExpressionHandler;

    @Autowired
    private AppAccessDeniedHandler appAccessDeniedHandler;

/*    @Bean
    @ConditionalOnExpression("'${personal.tokenStore.isNeedJWT}'=='true'")
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        return new JWTAuthenticationFilter(authenticationManager,personalProperties);
    } ;*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
      /*  if(personalProperties.getTokenStore().isNeedJWT() && jwtAuthenticationFilter()!= null){
            http.addFilter(jwtAuthenticationFilter());
        }*/
        // personalProperties.getAuth().toPostAdapter()
        // personalProperties.getAuth().toGetAdapter()

        http
                // 配置不需要验证的URL
                //配置post不需要验证的URL
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/oauth/token")
                .permitAll() //除上述URL,都需要登录用户 .and() .authorizeRequests() .anyRequest() .authenticated()
                .and()
                //配置get不需要验证的URL
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/oauth/token","/oauth/check_token")
                .permitAll()
                //除上述URL,都需要登录用户
                .and()
                .authorizeRequests()
                .antMatchers()
                .authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                //配置授权验证服务 ， 失败由 TODO  appAccessDeniedHandler 接收
                .access("@defaultZuulAuthorizationService.hasPermission(request,authentication)");
        ;
    }

    //和鉴权服务有关,springboot2.0新加入部分
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // TODO Auto-generated method stub
        resources
                .tokenServices(defaultTokenServices)
                .expressionHandler(appSecurityExpressionHandler) // 打开HttpSecurity.access @ 加入鉴权方式
                .accessDeniedHandler(appAccessDeniedHandler) // 接收 鉴权失败的信息
                .authenticationEntryPoint(new BootOAuth2AuthExceptionEntryPoint()) // token 验证失败
                .resourceId(DEMO_RESOURCE_ID).stateless(true)
                //.tokenStore(tokenStore)
                ;

    }




}
