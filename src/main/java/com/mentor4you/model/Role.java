package com.mentor4you.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MODERATOR,
    MENTOR,
    MENTEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
