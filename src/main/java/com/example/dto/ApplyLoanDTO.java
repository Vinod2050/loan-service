package com.example.dto;

import lombok.Data;

@Data
public class ApplyLoanDTO {

	private Integer customerId;
	private Integer cibilScore;
	private String firstName;
	private String lastName;
	private String customerEmail;
	private Boolean IsDocumentVerified;
	private Boolean IsCustometrVerified;

}
