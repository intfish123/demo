package com.intfish.securitydemo.config;

import com.intfish.securitydemo.Common.Consts;
import com.intfish.securitydemo.entity.Permission;
import com.intfish.securitydemo.entity.User;
import com.intfish.securitydemo.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class TokenFilter  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String token = req.getHeader(Consts.ACCESS_TOKEN);
        if(StringUtils.isEmpty(token)){
            token = req.getParameter(Consts.ACCESS_TOKEN);
        }
        if(StringUtils.isEmpty(token)){
            System.out.println("token 为空");
            SecurityContextHolder.getContext().setAuthentication(null);
        }else{
            User user = new User();
            user.setUid("1");
            user.setUsername("admin");
            Permission permission = new Permission();
            permission.setId(1);
            permission.setName("添加实验");
            permission.setPermit("flight:add");
            user.setAuthorities(Collections.singletonList(permission));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(req, res);
    }
}
