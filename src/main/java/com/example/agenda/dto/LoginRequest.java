package com.example.agenda.dto;

public class LoginRequest {

    private String email;
    private String senha;

    // Default constructor
    public LoginRequest() {}

    // Constructor with parameters
    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
