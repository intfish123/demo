package com.intfish.securitydemo.service;

import com.intfish.securitydemo.entity.Permission;
import com.intfish.securitydemo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User();
        user.setUid("1");
        user.setUsername("admin");
        user.setPassword("123");
        Permission permission = new Permission();
        permission.setId(1);
        permission.setName("添加实验");
        permission.setPermit("flight:add");
        user.setAuthorities(Collections.singletonList(permission));
        return user;
    }
}
