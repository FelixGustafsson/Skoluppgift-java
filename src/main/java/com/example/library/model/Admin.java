package com.example.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String username;
    private String password;
    private String role;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getAdminId() {
        return adminId;
    }
    public String getRole() {
        return role;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}