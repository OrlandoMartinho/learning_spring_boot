package com.example.crud.domain.user;

public record RegisterDTO(String login, String password,UserRole role) {
    public RegisterDTO {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
       
    }
   
    
}
