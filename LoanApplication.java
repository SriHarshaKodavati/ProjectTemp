package com.scb.Loan_Origination.Loan_Origination.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_application")
public class LoanApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalDetails personalDetails;
    
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmploymentDetails employmentDetails;
    
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LoanDetails loanDetails;
    
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DocumentDetails documentDetails;
    
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExistingLoanDetails existingLoanDetails;
    
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReferenceDetails referenceDetails;
    
    // Default constructor
    public LoanApplication() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Constructor with status
    public LoanApplication(String status) {
        this();
        this.status = status;
    }
    
    // Automatically update the updatedAt field before persisting
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now(); // Update timestamp when status changes
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }
    
    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
        if (personalDetails != null) {
            personalDetails.setLoanApplication(this);
        }
    }
    
    public EmploymentDetails getEmploymentDetails() {
        return employmentDetails;
    }
    
    public void setEmploymentDetails(EmploymentDetails employmentDetails) {
        this.employmentDetails = employmentDetails;
        if (employmentDetails != null) {
            employmentDetails.setLoanApplication(this);
        }
    }
    
    public LoanDetails getLoanDetails() {
        return loanDetails;
    }
    
    public void setLoanDetails(LoanDetails loanDetails) {
        this.loanDetails = loanDetails;
        if (loanDetails != null) {
            loanDetails.setLoanApplication(this);
        }
    }
    
    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }
    
    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
        if (documentDetails != null) {
            documentDetails.setLoanApplication(this);
        }
    }
    
    public ExistingLoanDetails getExistingLoanDetails() {
        return existingLoanDetails;
    }
    
    public void setExistingLoanDetails(ExistingLoanDetails existingLoanDetails) {
        this.existingLoanDetails = existingLoanDetails;
        if (existingLoanDetails != null) {
            existingLoanDetails.setLoanApplication(this);
        }
    }
    
    public ReferenceDetails getReferenceDetails() {
        return referenceDetails;
    }
    
    public void setReferenceDetails(ReferenceDetails referenceDetails) {
        this.referenceDetails = referenceDetails;
        if (referenceDetails != null) {
            referenceDetails.setLoanApplication(this);
        }
    }
    
    // Utility methods
    public boolean isDraft() {
        return "DRAFT".equalsIgnoreCase(this.status);
    }
    
    public boolean isSubmitted() {
        return "SUBMITTED".equalsIgnoreCase(this.status);
    }
    
    public boolean isApproved() {
        return "APPROVED".equalsIgnoreCase(this.status);
    }
    
    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(this.status);
    }
    
    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
