package com.example.library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String memberNumber;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    public String getMemberNumber() {
        return memberNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Long getUserId() {
        return userId;
    }
    public List<Loan> getLoans() {
        return loans;
    }
    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}