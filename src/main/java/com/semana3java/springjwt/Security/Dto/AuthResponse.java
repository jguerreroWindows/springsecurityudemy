package com.semana3java.springjwt.Security.Dto;

import java.util.Objects;

public class AuthResponse {

    private String toker;

    public AuthResponse(String toker) {
        this.toker = toker;
    }

    public String getToker() {
        return toker;
    }

    public void setToker(String toker) {
        this.toker = toker;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "toker='" + toker + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthResponse that)) return false;
        return Objects.equals(toker, that.toker);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(toker);
    }
}
