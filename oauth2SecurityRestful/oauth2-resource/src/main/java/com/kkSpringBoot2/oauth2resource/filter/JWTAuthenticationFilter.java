package com.kkSpringBoot2.oauth2resource.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Jwt 获取信息
 */

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

   // private PersonalProperties personalProperties;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /*public JWTAuthenticationFilter(AuthenticationManager authenticationManager, PersonalProperties personalProperties) {
        super(authenticationManager);
        this.personalProperties = personalProperties;
    }*/


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        System.out.println("header == "+ header);
        if (header == null || (!header.startsWith("Bearer ")&& !header.startsWith("bearer "))) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;
        try {
                //authentication = getAuthentication(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws  Exception{
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
  /*          String user = Jwts.parser()
                    .setSigningKey(personalProperties.getTokenStore().getTokenSigningKey().getBytes("UTF-8"))
                    .parseClaimsJws(token.replace("bearer ", ""))
                    .getBody()
                    .getSubject();*/

            Claims body = Jwts.parser()
                    .setSigningKey("com.kkSpringBoot2".getBytes("UTF-8"))// personalProperties.getTokenStore().getTokenSigningKey()
                    .parseClaimsJws(token.replace("bearer ", ""))
                    .getBody();


            System.out.println("user_name == "+ (String) body.get("user_name"));
            System.out.println("name == "+ (String) body.get("name"));
            System.out.println("id == "+ (String) body.get("id"));
            System.out.println("createAt == "+ (String) body.get("createAt"));
            //System.out.println("authorities == "+ (String) body.get("authorities"));
            String provider = (String) body.get("provider");
            System.out.println("provider == "+ provider);
            logger.info(provider);
            if (provider != null) {
                //此段是用于提取用户的认证authorities信息，具体操作看项目设计，
                // 如果不要sercuity验证可直接使用下段注释部分
                // 不需要sercuity验证
                return new UsernamePasswordAuthenticationToken(provider, null, new ArrayList<>());
                // 需要sercuity验证
               /* List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(10);
                String use =  user.replace("User(","").replace(")","");
                String[]  uselist =use.split(",");
                for(String a :uselist){
                    if(a.contains("roleName")){
                        String b = a.replace("roleName=","");
                        if(!StringUtils.isEmpty(b)){
                            for(String c: b.split(",")){
                                if(!StringUtils.isEmpty(c)){
                                    authorities.add(new SimpleGrantedAuthority(c.trim()));
                                }
                            }
                        }
                    }
                }
                return new UsernamePasswordAuthenticationToken(user, null, authorities);*/
            }
            return null;
        }
        return null;
    }

}
