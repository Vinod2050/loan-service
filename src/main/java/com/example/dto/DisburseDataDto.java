package com.example.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.enums.ConstructionStatus;

import lombok.Data;

@Data
public class DisburseDataDto {

	private Integer customerId;
	private String bankName;
	private Long accountNumber;
	private String ifscCode;
	private String accountHolderName;
	@Enumerated(EnumType.STRING)
	private ConstructionStatus constructionStatus;
    private Integer constructionPercentage;
	private Double downPaymentAmount;

}
