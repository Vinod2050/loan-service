package com.example.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.enums.LoanType;

import lombok.Data;

@Data
public class LoanApplicationDto {
	
	@Enumerated(EnumType.STRING)
	private LoanType loanType;
	private Double requestedAmount;
	private Integer requestedTenure;
	private Double  intrestRate;


}
