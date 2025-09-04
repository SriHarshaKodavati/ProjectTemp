package com.scb.Loan_Origination.Loan_Origination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scb.Loan_Origination.Loan_Origination.model.*;
import com.scb.Loan_Origination.Loan_Origination.service.LoanApplicationService;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"}) // Add your frontend URL
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    // --- Create new application ---
    @PostMapping("/create")
    public ResponseEntity<LoanApplication> createApplication() {
        try {
            LoanApplication application = loanApplicationService.createNewApplication();
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Save Personal Details ---
    @PostMapping("/{id}/personal-details")
    public ResponseEntity<LoanApplication> savePersonalDetails(
            @PathVariable Long id,
            @RequestBody PersonalDetails details) {
        try {
            LoanApplication application = loanApplicationService.savePersonalDetails(id, details);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Save Employment Details ---
    @PostMapping("/{id}/employment-details")
    public ResponseEntity<LoanApplication> saveEmploymentDetails(
            @PathVariable Long id,
            @RequestBody EmploymentDetails details) {
        try {
            LoanApplication application = loanApplicationService.saveEmploymentDetails(id, details);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Save Loan Details ---
    @PostMapping("/{id}/loan-details")
    public ResponseEntity<LoanApplication> saveLoanDetails(
            @PathVariable Long id,
            @RequestBody LoanDetails details) {
        try {
            LoanApplication application = loanApplicationService.saveLoanDetails(id, details);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Save Document Details ---
    @PostMapping("/{id}/document-details")
    public ResponseEntity<LoanApplication> saveDocumentDetails(
            @PathVariable Long id,
            @RequestBody DocumentDetails details) {
        try {
            LoanApplication application = loanApplicationService.saveDocumentDetails(id, details);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Save Existing Loan Details ---
    @PostMapping("/{id}/existing-loan-details")
    public ResponseEntity<LoanApplication> saveExistingLoanDetails(
            @PathVariable Long id,
            @RequestBody ExistingLoanDetails details) {
        try {
            LoanApplication application = loanApplicationService.saveExistingLoanDetails(id, details);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Save Reference Details ---
    @PostMapping("/{id}/reference-details")
    public ResponseEntity<LoanApplication> saveReferenceDetails(
            @PathVariable Long id,
            @RequestBody ReferenceDetails details) {
        try {
            LoanApplication application = loanApplicationService.saveReferenceDetails(id, details);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Update Application Status ---
    @PutMapping("/{id}")
    public ResponseEntity<LoanApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        try {
            String status = statusUpdate.get("status");
            LoanApplication application = loanApplicationService.updateApplicationStatus(id, status);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --- Get Application by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> getApplication(@PathVariable Long id) {
        try {
            LoanApplication application = loanApplicationService.getApplicationById(id);
            return ResponseEntity.ok(application);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- Get All Applications (optional for admin purposes) ---
    @GetMapping
    public ResponseEntity<Iterable<LoanApplication>> getAllApplications() {
        try {
            // You might want to add pagination here
            return ResponseEntity.ok(loanApplicationService.getAllApplications());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
