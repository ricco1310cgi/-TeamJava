package com.cgi.smartcv.security.dto;

import com.cgi.smartcv.security.SecurityHandshake;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String token;
    private String role;

    public Tokens() {
    }

    public Tokens(SecurityHandshake securityHandshake) {
        this.name = securityHandshake.getUsername();
        this.token = securityHandshake.getAuthToken();
        this.role = securityHandshake.getRole();
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
