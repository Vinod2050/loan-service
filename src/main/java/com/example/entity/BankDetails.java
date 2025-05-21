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
public class BankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankDetailsId;
    private Integer customerId;
	private String bankName;
	private Long accountNumber;
	private String ifscCode;
	private String accountHolderName;
	@OneToOne
	private LoanApplication loanApplication;

}
