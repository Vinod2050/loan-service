package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanApprovalId;
	private Integer customerId;
	private Boolean isAllPropertyDocumentVerified=false;
	private Boolean isIncomeProofVerified=false;
	private Boolean isGarantorVerified=false;
	private Boolean isCibilScoreGood=false;
	private Boolean isLoanStatusApproved=false;
	@OneToOne
	private LoanApplication loanApplication;
	
	

}
