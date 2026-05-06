package com.semana3java.springjwt.Security.Dto;

import java.util.Objects;

public class AuthResponse {

    private String token;

    public AuthResponse(String toker) {
        this.token = toker;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String toker) {
        this.token = toker;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "toker='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthResponse that)) return false;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }
}
