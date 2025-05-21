package com.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.enums.LoanStatus;
import com.example.enums.LoanType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanApplicationId;
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String customerEmail;
	@Enumerated(EnumType.STRING)
	private LoanType loanType;
	private Double requestedAmount;
	private Integer requestedTenure;
	private Double  intrestRate;
	private Double downPaymentAmount;
	private Double monthlyIncome;
	private LocalDate applicationDate;
	@Enumerated(EnumType.STRING)
	private LoanStatus loanStatus;
    private Integer cibilScore;
    private Boolean IsDocumentVerified ;
    private Boolean IsCustometrVerified;

   




	

}
