package com.intfish.securitydemo.config;

import com.alibaba.fastjson.JSON;
import com.intfish.securitydemo.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenFilter tokenFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
//                .formLogin()
//                .failureHandler((req, res, e) -> {
//                    res.setContentType("application/json;charset=utf-8");
//                    PrintWriter writer = res.getWriter();
//                    R error = R.error(HttpStatus.FORBIDDEN);
//                    writer.write(JSON.toJSONString(error));
//                    writer.flush();
//                })
//                .and()
                .authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(tokenFilter, LogoutFilter.class)
                .exceptionHandling().accessDeniedHandler((req, res, e) -> {
            res.setContentType("application/json;charset=utf-8");
            PrintWriter writer = res.getWriter();
            R error = R.error(HttpStatus.FORBIDDEN);
                    writer.write(JSON.toJSONString(error));
                    writer.flush();
        });
    }
}
