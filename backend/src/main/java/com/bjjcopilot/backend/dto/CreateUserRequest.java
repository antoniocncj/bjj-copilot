package com.bjjcopilot.backend.dto;

import com.bjjcopilot.backend.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Size(max = 100)
    private String name;
    
    @NotNull
    private UserRole role;
    
    // Constructors
    public CreateUserRequest() {}
    
    public CreateUserRequest(String email, String name, UserRole role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }
    
    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}