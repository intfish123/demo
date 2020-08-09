package com.intfish.securitydemo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Permission implements GrantedAuthority {
    private Integer id;
    private String name;
    private String permit;
    @Override
    public String getAuthority() {
        return permit;
    }
}
