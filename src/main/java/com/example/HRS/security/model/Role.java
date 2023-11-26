package com.example.HRS.security.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private final String role;

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
