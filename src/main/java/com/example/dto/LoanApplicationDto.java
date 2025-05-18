package com.example.dto;

import lombok.Data;

@Data
public class LoanApplicationDto {

	private Double requestedAmount;
	private Integer requestedTenure;
	private Double  intrestRate;
	private String firstName;
	private String lastName;
	private String customerEmail;
	private Double monthlyIncome;
	private Double downPaymentAmount;


}
