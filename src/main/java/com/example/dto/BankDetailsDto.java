package com.example.dto;

import lombok.Data;

@Data
public class BankDetailsDto {
	private String bankName;
	private Long accountNumber;
	private String ifscCode;
	private String accountHolderName;
}
