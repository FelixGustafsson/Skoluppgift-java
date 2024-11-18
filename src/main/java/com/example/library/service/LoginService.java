package com.example.library.service;

import com.example.library.model.Admin;
import com.example.library.model.User;
import com.example.library.repository.AdminRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    private String currentUserRole;

    public String login(String usernameOrEmail, String password) throws Exception {

        Admin admin = adminRepository.findByUsername(usernameOrEmail);
        if (admin != null && admin.getPassword().equals(password)) {
            currentUserRole = "ADMIN";
            return "Admin logged in as " + admin.getUsername();
        }


        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null && user.getMemberNumber().equals(password)) {
            currentUserRole = "USER";
            return "User logged in as " + user.getEmail();
        }

        throw new Exception("Invalid username/email or password.");
    }

    public String getCurrentUserRole() {
        return currentUserRole;
    }

    public boolean isAdmin() {
        return "ADMIN".equals(currentUserRole);
    }
}