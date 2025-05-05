package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.LoanApplication;
import com.example.entity.PropertyDetails;

public interface LoanRepository extends JpaRepository<LoanApplication, Integer>{

	//PropertyDetails findbyLoanApplication(PropertyDetails loanApplication);

	LoanApplication findByCustomerId(Integer customerId);
	


	
}