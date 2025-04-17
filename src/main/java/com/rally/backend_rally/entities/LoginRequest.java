package com.rally.backend_rally.entities;

public class LoginRequest {
    private String alias;
    private String password;

    public LoginRequest() {}

    public LoginRequest(String alias, String password) {
        this.alias = alias;
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
