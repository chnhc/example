package com.kkSpringBoot2.oauth2resource.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限不够， 访问拒绝
 */
@Component("appAccessDeniedHandler")
public class AppAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
     /*   response.setContentType(ZuulAppConstant.CONTENT_TYPE_JSON);
        response.setStatus(HttpStatus.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(ResultVo.createErrorResult("当前用户访问权限不够,请联系管理员增加对应权限",403)));
*/
       /* response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("当前用户访问权限不够,请联系管理员增加对应权限")));
*/
        response.setContentType("application/json;charset=UTF-8");
        Map map = new HashMap();
        map.put("error", "40140");
        map.put("message", accessDeniedException.getMessage());
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(new Date().getTime()));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(map));
        /*response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(HttpResponse.simpleResponse(BaseResponseStatus.USER_PRIVILEGE_FAIL.status)));
*/

    }

}

