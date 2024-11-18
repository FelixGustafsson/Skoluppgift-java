package com.example.library.service;

import com.example.library.dto.LoanDTO;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    public void loanBook(Long bookId, Long userId) {
        if (bookRepository.existsByBookIdAndAvailableTrue(bookId)) {
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Loan loan = new Loan();
            loan.setBook(book);
            loan.setUser(user);
            loan.setLoanDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusMonths(1);
            loan.setDueDate(Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            loanRepository.save(loan);
            book.setAvailable(false);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book is not available for loan.");
        }
    }

    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setReturnedDate(new Date());
        loanRepository.save(loan);

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
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

    public List<Loan> getActiveLoans(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return loanRepository.findByUserAndReturnedDateIsNull(user);
    }

    public List<LoanDTO> getAllActiveLoans() {
        List<Loan> allActiveLoans = loanRepository.findAll().stream()
                .filter(loan -> loan.getReturnedDate() == null) // Keep loans that are not returned
                .collect(Collectors.toList());

        return allActiveLoans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}