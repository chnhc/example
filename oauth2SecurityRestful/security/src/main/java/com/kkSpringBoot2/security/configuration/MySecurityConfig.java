package com.kkSpringBoot2.security.configuration;

import com.kkSpringBoot2.oauth2Security.Services.user.UserService;
import com.kkSpringBoot2.security.userdetails.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description: 自定义security认证
 * author: ckk
 * create: 2019-03-31 09:49
 */
@Configuration
@Order(5)
@EnableWebSecurity
//来判断用户对某个控制层的方法是否具有访问权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 生成定义一个PasswordEncoder的Bean，配置加密方式，这里生成BCryptPasswordEncoder的Bean对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 /*   @Autowired(required = false)
    PasswordEncoder PasswordEncoder;*/

    @Autowired
    UserService userService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 生成一个自定义的UserDetailsServiceImpl的Bean，交给Spring IOC容器
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(passwordEncoder(), userService);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()) // 用户认证
                .passwordEncoder(passwordEncoder()); // 使用加密验证
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers(HttpMethod.OPTIONS, "kkSpringBoot2/oauth/token", "/rest/**", "/api/**", "/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()// 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/kkSpringBoot2/oauth/**").permitAll()// 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest()
                // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .cors()
                .and().csrf().disable();// 关闭csrf 跨站伪造防护

    }


}
