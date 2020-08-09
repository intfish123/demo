package com.intfish.securitydemo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;
    private String code;

    @Override
    public String getAuthority() {
        return "ROLE_" + code;
    }
}
