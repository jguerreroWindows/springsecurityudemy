package com.semana3java.springjwt.Security.Dto;

import com.semana3java.springjwt.Enums.RolEnum;

import java.util.Objects;
import java.util.Set;

public class RegistroRequest {

    private String username;
    private String password;
    private Set<RolEnum>roles;

    public RegistroRequest(Set<RolEnum> roles, String password, String username) {
        this.roles = roles;
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RolEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEnum> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "RegistroRequest{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RegistroRequest that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, roles);
    }
}
