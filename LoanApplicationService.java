package com.scb.Loan_Origination.Loan_Origination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scb.Loan_Origination.Loan_Origination.model.*;
import com.scb.Loan_Origination.Loan_Origination.repository.LoanApplicationRepository;

@Service
public class LoanApplicationService {
    
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    
    public LoanApplication createNewApplication() {
        LoanApplication application = new LoanApplication();
        application.setStatus("DRAFT");
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication savePersonalDetails(Long applicationId, PersonalDetails details) {
        LoanApplication application = getApplicationById(applicationId);
        
        // If personal details already exist, update them
        if (application.getPersonalDetails() != null) {
            PersonalDetails existingDetails = application.getPersonalDetails();
            existingDetails.setFullName(details.getFullName());
            existingDetails.setPhoneNumber(details.getPhoneNumber());
            existingDetails.setMaritalStatus(details.getMaritalStatus());
            existingDetails.setPanNumber(details.getPanNumber());
            existingDetails.setPassportNumber(details.getPassportNumber());
            existingDetails.setGender(details.getGender());
            existingDetails.setCurrentAddress(details.getCurrentAddress());
            existingDetails.setPermanentAddress(details.getPermanentAddress());
            existingDetails.setDateOfBirth(details.getDateOfBirth());
        } else {
            details.setLoanApplication(application);
            application.setPersonalDetails(details);
        }
        
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication saveEmploymentDetails(Long applicationId, EmploymentDetails details) {
        LoanApplication application = getApplicationById(applicationId);
        
        // If employment details already exist, update them
        if (application.getEmploymentDetails() != null) {
            EmploymentDetails existingDetails = application.getEmploymentDetails();
            existingDetails.setEmploymentType(details.getEmploymentType());
            existingDetails.setEmployerName(details.getEmployerName());
            existingDetails.setJobTitle(details.getJobTitle());
            existingDetails.setMonthlyIncome(details.getMonthlyIncome());
            existingDetails.setUnemploymentIncome(details.getUnemploymentIncome());
            existingDetails.setYearsOfExperience(details.getYearsOfExperience());
            existingDetails.setOfficeAddress(details.getOfficeAddress());
        } else {
            details.setLoanApplication(application);
            application.setEmploymentDetails(details);
        }
        
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication saveLoanDetails(Long applicationId, LoanDetails details) {
        LoanApplication application = getApplicationById(applicationId);
        
        // If loan details already exist, update them
        if (application.getLoanDetails() != null) {
            LoanDetails existingDetails = application.getLoanDetails();
            existingDetails.setLoanType(details.getLoanType());
            existingDetails.setLoanAmount(details.getLoanAmount());
            existingDetails.setLoanTenure(details.getLoanTenure());
            existingDetails.setLoanPurpose(details.getLoanPurpose());
        } else {
            details.setLoanApplication(application);
            application.setLoanDetails(details);
        }
        
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication saveDocumentDetails(Long applicationId, DocumentDetails details) {
        LoanApplication application = getApplicationById(applicationId);
        
        // If document details already exist, update them
        if (application.getDocumentDetails() != null) {
            DocumentDetails existingDetails = application.getDocumentDetails();
            existingDetails.setPanCardPath(details.getPanCardPath());
            existingDetails.setAadhaarCardPath(details.getAadhaarCardPath());
            existingDetails.setSalarySlipPath(details.getSalarySlipPath());
            existingDetails.setBankStatementPath(details.getBankStatementPath());
            existingDetails.setPhotoPath(details.getPhotoPath());
        } else {
            details.setLoanApplication(application);
            application.setDocumentDetails(details);
        }
        
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication saveExistingLoanDetails(Long applicationId, ExistingLoanDetails details) {
        LoanApplication application = getApplicationById(applicationId);
        
        // If existing loan details already exist, update them
        if (application.getExistingLoanDetails() != null) {
            ExistingLoanDetails existingDetails = application.getExistingLoanDetails();
            existingDetails.setFullName(details.getFullName());
            existingDetails.setLoanType(details.getLoanType());
            existingDetails.setContactNumber(details.getContactNumber());
            existingDetails.setLender(details.getLender());
            existingDetails.setEmail(details.getEmail());
            existingDetails.setOutstandingAmount(details.getOutstandingAmount());
            existingDetails.setAddress(details.getAddress());
            existingDetails.setEmiTenure(details.getEmiTenure());
        } else {
            details.setLoanApplication(application);
            application.setExistingLoanDetails(details);
        }
        
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication saveReferenceDetails(Long applicationId, ReferenceDetails details) {
        LoanApplication application = getApplicationById(applicationId);
        
        // If reference details already exist, update them
        if (application.getReferenceDetails() != null) {
            ReferenceDetails existingDetails = application.getReferenceDetails();
            existingDetails.setFullName(details.getFullName());
            existingDetails.setRelationshipWithApplicant(details.getRelationshipWithApplicant());
            existingDetails.setContactNumber(details.getContactNumber());
            existingDetails.setAddress(details.getAddress());
        } else {
            details.setLoanApplication(application);
            application.setReferenceDetails(details);
        }
        
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication updateApplicationStatus(Long applicationId, String status) {
        LoanApplication application = getApplicationById(applicationId);
        application.setStatus(status);
        return loanApplicationRepository.save(application);
    }
    
    public LoanApplication getApplicationById(Long id) {
        return loanApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }
    
    public Iterable<LoanApplication> getAllApplications() {
        return loanApplicationRepository.findAll();
    }
}
