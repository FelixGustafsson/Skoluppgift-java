package com.example.library.controller;

import com.example.library.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestParam String usernameOrEmail, @RequestParam String password) throws Exception {
        return loginService.login(usernameOrEmail, password);
    }

    @GetMapping("/role")
    public String getCurrentUserRole() {
        return loginService.getCurrentUserRole();
    }
}