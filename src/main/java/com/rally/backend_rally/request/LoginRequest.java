package com.rally.backend_rally.request;

// Clase para los datos que se envían en un login (alias y contraseña)
public class LoginRequest {
    private String alias;
    private String password;

    // Constructor vacío
    public LoginRequest() {}

    // Constructor con parámetros
    public LoginRequest(String alias, String password) {
        this.alias = alias;
        this.password = password;
    }

    // Getters y Setters
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
