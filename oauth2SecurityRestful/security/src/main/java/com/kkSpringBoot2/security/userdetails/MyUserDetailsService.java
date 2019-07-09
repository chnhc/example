package com.kkSpringBoot2.security.userdetails;


import com.kkSpringBoot2.oauth2Security.DAOS.entity.user.UserEntity;
import com.kkSpringBoot2.oauth2Security.Services.user.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 自定义UserDetailsService
 * author: ckk
 * create: 2019-03-31 09:41
 */

public class MyUserDetailsService implements UserDetailsService {

    private PasswordEncoder PasswordEncoder;

    private UserService userService;

    public MyUserDetailsService(PasswordEncoder PasswordEncoder , UserService userService ) {
        this.PasswordEncoder = PasswordEncoder;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity userEntity = userService.selectAdmin();

        if(userEntity.getUser_name().equals(s)) {
            if (PasswordEncoder == null) {
                PasswordEncoder = new BCryptPasswordEncoder();
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_admin"));

            return new User(userEntity.getUser_name(),
                    userEntity.getUser_pwd(),
                    authorities);
        }else{
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            return new User(userEntity.getUser_name(),
                    "",
                    authorities);
        }

    }
}
