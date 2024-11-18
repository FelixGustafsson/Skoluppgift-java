package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserAndReturnedDateIsNull(User user);
    List<Loan> findByBookAndReturnedDateIsNull(Book book);
}