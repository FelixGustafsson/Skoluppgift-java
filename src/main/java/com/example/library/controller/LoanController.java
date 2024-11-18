package com.example.library.controller;

import com.example.library.dto.LoanDTO;
import com.example.library.model.Loan;
import com.example.library.service.LoanService;
import com.example.library.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/loan")
    public void loanBook(@RequestParam Long bookId, @RequestParam Long userId) {
        loanService.loanBook(bookId, userId);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long loanId) {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        loanService.returnBook(loanId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active/{userId}")
    public List<Loan> getActiveLoans(@PathVariable Long userId) {
        return loanService.getActiveLoans(userId);
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<LoanDTO>> getAllActiveLoans() {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().body(loanService.getAllActiveLoans());
    }


}