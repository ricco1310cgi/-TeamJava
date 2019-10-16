package com.cgi.smartcv.security;

public class SecurityHandshake {
    private String username;
    private String authToken;
    private String role;

    public SecurityHandshake(String username, String authToken, String role) {
        this.username = username;
        this.authToken = authToken;
        this.role = role;
    }

    public SecurityHandshake() {
    }

    @Override
    public String toString() {
        return "SecurityHandshake{" +
                "username='" + username + '\'' +
                ", authToken='" + authToken + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
