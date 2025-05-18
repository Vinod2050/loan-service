package com.example.service;

import java.util.List;

import com.example.dto.ApplyLoanDTO;
import com.example.dto.DisburseDataDto;
import com.example.dto.LoanApplicationDto;
import com.example.entity.LoanApplication;
import com.example.enums.LoanType;


public interface LoanService {

	String addApplicant(ApplyLoanDTO loanDto);

	String completeApplication(Integer customerId, LoanType loanType, LoanApplicationDto loanApplicationDto);

	String updateLoanApplication(Integer loanApplicationId, LoanType loanType, LoanApplicationDto loanApplicationDto);

	String deleteLoanApplication(Integer loanApplicationId);

	LoanApplication getLoanApplicationById(Integer loanApplicationId);

	List<LoanApplication> getAllLoanApplications();

	DisburseDataDto getDisburseData(Integer customerId);

}
