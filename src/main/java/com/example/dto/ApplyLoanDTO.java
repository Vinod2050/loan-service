package com.example.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class ApplyLoanDTO {

	private Integer customerId;
	private Integer cibilScore;
	private Boolean IsDocumentVerified;
	private Boolean IsCustometrVerified;

}
