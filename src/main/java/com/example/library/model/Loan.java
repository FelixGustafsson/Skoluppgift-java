package com.example.library.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private Date loanDate;
    private Date dueDate;
    private Date returnedDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setLoanDate(Date date) {
        this.loanDate = date;
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setReturnedDate(Date date) {
        this.returnedDate = date;
    }

    public Book getBook() {
        return book;
    }

    public Date getDueDate() {
        return dueDate;
    }
    public Date getLoanDate() {
        return loanDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }
    public Long getLoanId() {
        return loanId;
    }
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
    public User getUser() {
        return user;
    }

}