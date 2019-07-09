package com.kkSpringBoot2.oauth2resource.configuration.service;

import com.kkSpringBoot2.oauth2resource.configuration.service.imp.ZuulAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户通过验证 ， 进入此方法鉴权
 */

@Component("defaultZuulAuthorizationService")
public class DefaultZuulAuthorizationService implements ZuulAuthorizationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultZuulAuthorizationService.class);

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // boolean isPermission = false;
         boolean isPermission = false;
        String requestURI = request.getRequestURI();
        if (StringUtils.isEmpty(requestURI)) {
            return isPermission;
        }
        LOGGER.info("用户[{}]鉴权,鉴权地址为:{}.", "测试", requestURI);


        //匿名用户不能经过授权
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser") &&
                authentication.isAuthenticated()) {

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {

                for(GrantedAuthority grantedAuthority:((UserDetails) principal).getAuthorities()){
                    // 用户权限 是admin
                    if(grantedAuthority.toString().contains("admin")){
                        isPermission = true;
                    }

                }
                //System.out.println( " UserDetails = "+((UserDetails) principal).getUsername());
            }
            /*String userName = "";

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                userName= ((UserDetails) principal).getUsername();

                for(GrantedAuthority grantedAuthority:((UserDetails) principal).getAuthorities()){
                    System.out.println("用户权限 = " + grantedAuthority);
                }
                //System.out.println( " UserDetails = "+((UserDetails) principal).getUsername());
            }
            if (principal instanceof Principal) {
                userName= ((Principal) principal).getName();
                System.out.println(" principal = "+((Principal) principal).getName());
            }

            //读取用户所有的Url,可以通过用户服务拿到当前用户服务拿到该用户的能访问的地址
            Set<String> urls = new HashSet<>();
            urls.add("/test1/hello");
            for (String url : urls) {
                if (antPathMatcher.match(url, requestURI)) {
                    isPermission = true;
                    LOGGER.info("用户[{}]鉴权,鉴权地址为:{}.", userName, requestURI);
                    break;
                }
            }*/
            return isPermission;
        }
        return isPermission;
    }
}


