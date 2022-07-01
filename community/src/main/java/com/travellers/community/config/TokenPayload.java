package com.travellers.community.config;

import com.travellers.community.model.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class TokenPayload {

    private final String username;
    private final Role role;

    public TokenPayload(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public List<Role> getRole() {
        return (List<Role>) role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("role", role)
                .toString();
    }
}
