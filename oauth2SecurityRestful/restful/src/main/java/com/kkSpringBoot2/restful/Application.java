package com.kkSpringBoot2.restful;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * description: 启动类
 * author: ckk
 * create: 2019-03-31 09:16
 */
@SpringBootApplication(scanBasePackages = {
        "com.kkSpringBoot2.restful",                    // 导入接口
        "com.kkSpringBoot2.other",                      // 导入 其他数据库 数据操作
        "com.kkSpringBoot2.oauth2Security",            // 导入oauth2-security 数据操作
        "com.kkSpringBoot2.security",                   // 导入自定义 security 安全
        "com.kkSpringBoot2.oauth2Common",              // 导入自定义Oauth2 公共资源包
        "com.kkSpringBoot2.oauth2Server",              // 导入自定义Oauth2 安全认证服务
        "com.kkSpringBoot2.oauth2resource",            // 导入自定义Oauth2 安全资源服务



}, exclude = DataSourceAutoConfiguration.class)

@MapperScan(value = {
        "com.kkSpringBoot2.other.DAOS",
        "com.kkSpringBoot2.oauth2Security.DAOS",


})    // mybatis 扫描包 ( 这是注意点 )


@EnableScheduling  //启动定时任务
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }

    /**
     * 解决前后端分离跨域问题
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


}
