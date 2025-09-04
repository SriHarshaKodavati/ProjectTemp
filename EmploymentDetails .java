package com.scb.Loan_Origination.Loan_Origination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "employment_details")
public class EmploymentDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String employmentType;
    private String employerName;
    private String jobTitle;
    private Double monthlyIncome;
    private Double unemploymentIncome;
    private Integer yearsOfExperience;
    private String officeAddress; // Added missing field
    
    @OneToOne
    @JoinColumn(name = "loan_application_id")
    @JsonIgnore
    private LoanApplication loanApplication;
    
    // Default constructor
    public EmploymentDetails() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmploymentType() {
        return employmentType;
    }
    
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
    
    public String getEmployerName() {
        return employerName;
    }
    
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }
    
    public String getJobTitle() {
        return jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public Double getMonthlyIncome() {
        return monthlyIncome;
    }
    
    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    
    public Double getUnemploymentIncome() {
        return unemploymentIncome;
    }
    
    public void setUnemploymentIncome(Double unemploymentIncome) {
        this.unemploymentIncome = unemploymentIncome;
    }
    
    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }
    
    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public String getOfficeAddress() {
        return officeAddress;
    }
    
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }
    
    public LoanApplication getLoanApplication() {
        return loanApplication;
    }
    
    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
