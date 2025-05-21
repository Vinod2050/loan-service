package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApprovalDto {
	private Integer customerId;
	private Boolean isAllPropertyDocumentVerified;
	private Boolean isIncomeProofVerified;
	private Boolean isGarantorVerified;
	private Boolean isCibilScoreGood;
	private Boolean isLoanStatusApproved;
	 
}
