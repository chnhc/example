package com.kkSpringBoot2.oauth2resource.configuration.service.imp;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权服务接口
 */
public interface ZuulAuthorizationService {
    /**
     * 验证用户是否有权登录
     *
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

