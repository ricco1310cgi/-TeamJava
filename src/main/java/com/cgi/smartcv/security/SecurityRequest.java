package com.cgi.smartcv.security;

public class SecurityRequest {
    private String username;
    private String password;

    public SecurityRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SecurityRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SecurityRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
