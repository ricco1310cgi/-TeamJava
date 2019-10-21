package com.cgi.smartcv.security;

public class LogoutRequest {
    private String username;
    private String token;

    public LogoutRequest() {
    }

    public LogoutRequest(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LogoutRequest{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
