package com.example.service;

import java.util.List;

import com.example.dto.ApplyLoanDTO;
import com.example.dto.LoanApplicationDto;
import com.example.entity.LoanApplication;


public interface LoanService {

	String addApplicant(ApplyLoanDTO loanDto);

	String completeApplication(Integer customerId, LoanApplicationDto loanApplicationDto);

	String updateLoanApplication(Integer loanApplicationId, LoanApplicationDto loanApplicationDto);

	String deleteLoanApplication(Integer loanApplicationId);

	LoanApplication getLoanApplicationById(Integer loanApplicationId);

	List<LoanApplication> getAllLoanApplications();

}
