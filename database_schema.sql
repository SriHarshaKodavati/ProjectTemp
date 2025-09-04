-- PostgreSQL Database Schema for Loan Application System

-- Create the database (run this separately if needed)
-- CREATE DATABASE loan_origination_db;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS document_details CASCADE;
DROP TABLE IF EXISTS employment_details CASCADE;
DROP TABLE IF EXISTS exiting_loan_details CASCADE;
DROP TABLE IF EXISTS loan_details CASCADE;
DROP TABLE IF EXISTS personal_details CASCADE;
DROP TABLE IF EXISTS reference_details CASCADE;
DROP TABLE IF EXISTS loan_application CASCADE;

-- Create loan_application table
CREATE TABLE loan_application (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(50) DEFAULT 'DRAFT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create trigger for updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_loan_application_updated_at 
    BEFORE UPDATE ON loan_application 
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Create personal_details table
CREATE TABLE personal_details (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    marital_status VARCHAR(50),
    pan_number VARCHAR(10),
    passport_number VARCHAR(20),
    gender VARCHAR(10),
    current_address TEXT,
    permanent_address TEXT,
    date_of_birth DATE,
    loan_application_id BIGINT UNIQUE,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id) ON DELETE CASCADE
);

-- Create employment_details table
CREATE TABLE employment_details (
    id BIGSERIAL PRIMARY KEY,
    employment_type VARCHAR(50),
    employer_name VARCHAR(255),
    job_title VARCHAR(255),
    monthly_income DECIMAL(15,2),
    unemployment_income DECIMAL(15,2),
    years_of_experience INTEGER,
    office_address TEXT,
    loan_application_id BIGINT UNIQUE,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id) ON DELETE CASCADE
);

-- Create loan_details table
CREATE TABLE loan_details (
    id BIGSERIAL PRIMARY KEY,
    loan_type VARCHAR(50),
    loan_amount DECIMAL(15,2),
    loan_tenure INTEGER,
    loan_purpose TEXT,
    loan_application_id BIGINT UNIQUE,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id) ON DELETE CASCADE
);

-- Create document_details table
CREATE TABLE document_details (
    id BIGSERIAL PRIMARY KEY,
    pan_card_path VARCHAR(500),
    aadhaar_card_path VARCHAR(500),
    salary_slip_path VARCHAR(500),
    bank_statement_path VARCHAR(500),
    photo_path VARCHAR(500),
    loan_application_id BIGINT UNIQUE,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id) ON DELETE CASCADE
);

-- Create exiting_loan_details table (keeping original name from your model)
CREATE TABLE exiting_loan_details (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    loan_type VARCHAR(50),
    contact_number VARCHAR(15),
    lender VARCHAR(255),
    email VARCHAR(255),
    outstanding_amount DECIMAL(15,2),
    address TEXT,
    emi_tenure INTEGER,
    loan_application_id BIGINT UNIQUE,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id) ON DELETE CASCADE
);

-- Create reference_details table
CREATE TABLE reference_details (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    relationship_with_applicant VARCHAR(100),
    contact_number VARCHAR(15),
    address TEXT,
    loan_application_id BIGINT UNIQUE,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id) ON DELETE CASCADE
);

-- Insert some sample data for testing (optional)
INSERT INTO loan_application (status) VALUES ('DRAFT');

-- Add indexes for better performance
CREATE INDEX idx_loan_app_status ON loan_application(status);
CREATE INDEX idx_personal_pan ON personal_details(pan_number);
CREATE INDEX idx_personal_phone ON personal_details(phone_number);
CREATE INDEX idx_employment_type ON employment_details(employment_type);
CREATE INDEX idx_loan_type ON loan_details(loan_type);
CREATE INDEX idx_reference_contact ON reference_details(contact_number);

-- Verify tables were created
SELECT table_name FROM information_schema.tables 
WHERE table_schema = 'public' 
ORDER BY table_name;
