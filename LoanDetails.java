package com.scb.Loan_Origination.Loan_Origination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "loan_details")
public class LoanDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String loanType; // Added missing field
    private Double loanAmount;
    private Integer loanTenure;
    private String loanPurpose;
    
    @OneToOne
    @JoinColumn(name = "loan_application_id")
    @JsonIgnore
    private LoanApplication loanApplication;
    
    // Default constructor
    public LoanDetails() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLoanType() {
        return loanType;
    }
    
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    
    public Double getLoanAmount() {
        return loanAmount;
    }
    
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    public Integer getLoanTenure() {
        return loanTenure;
    }
    
    public void setLoanTenure(Integer loanTenure) {
        this.loanTenure = loanTenure;
    }
    
    public String getLoanPurpose() {
        return loanPurpose;
    }
    
    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }
    
    public LoanApplication getLoanApplication() {
        return loanApplication;
    }
    
    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
