package com.example.library.dto;

import java.util.Date;

public class LoanDTO {
    private Long loanId;
    private Long userId;
    private BookDTO book;
    private Date loanDate;
    private Date dueDate;
    private Date returnedDate;

    public LoanDTO(Long loanId, Long userId, BookDTO book, Date loanDate, Date dueDate, Date returnedDate) {
        this.loanId = loanId;
        this.userId = userId;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    public Long getLoanId() {
        return loanId;
    }
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public BookDTO getBook() {
        return book;
    }
    public void setBook(BookDTO book) {
        this.book = book;
    }
    public Date getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getReturnedDate() {
        return returnedDate;
    }
    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
}
