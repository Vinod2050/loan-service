package com.example.service;

import com.example.dto.LoanApprovalDto;
import com.example.dto.LoanSanctionDto;
import com.example.entity.LoanApproval;

public interface LoanApprovalService {

	String addLoanStatus(LoanApprovalDto loanApprovalDto);

	String loanSanction(Integer customerId);


}
