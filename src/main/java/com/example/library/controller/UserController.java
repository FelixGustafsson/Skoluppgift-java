package com.example.library.controller;

import com.example.library.dto.LoanDTO;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import com.example.library.service.BookService;
import com.example.library.service.LoginService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User newUser = userService.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanDTO>> getAllUserLoans(@PathVariable Long userId) {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.getAllUserLoans(userId));
    }

    @GetMapping("/dueDates/{userId}")
    public ResponseEntity<List<Date>> getLoanDueDates(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getReturnDates(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> putUser(@PathVariable Long userId, @RequestBody User user) {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (userRepository.findById(userId).isPresent()) {
            return ResponseEntity.ok(userService.updateUser(userId, user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}