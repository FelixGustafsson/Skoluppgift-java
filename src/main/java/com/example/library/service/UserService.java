package com.example.library.service;

import com.example.library.dto.LoanDTO;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.*;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public List<LoanDTO> getAllUserLoans(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Loan> userLoans = loanRepository.findByUserAndReturnedDateIsNull(user);
        return userLoans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LoanDTO convertToDTO(Loan userLoan) {
        return new LoanDTO(
                userLoan.getLoanId(),
                userLoan.getUser().getUserId(),
                bookService.convertToDTO(userLoan.getBook()),
                userLoan.getLoanDate(),
                userLoan.getDueDate(),
                userLoan.getReturnedDate()
        );
    }

    public Date getLoanDueDate(Loan userLoan) {
        return userLoan.getDueDate();
    }

    public List<Date> getReturnDates(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Loan> loans = loanRepository.findByUserAndReturnedDateIsNull(user);
        return loans.stream()
                .map(this::getLoanDueDate)
                .collect(Collectors.toList());
    }
}