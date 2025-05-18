package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApplyLoanDTO;
import com.example.dto.DisburseDataDto;
import com.example.dto.LoanApplicationDto;
import com.example.entity.LoanApplication;
import com.example.enums.LoanType;
import com.example.service.LoanService;

@RestController
@RequestMapping(value = "/api/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

	@PostMapping
	public ResponseEntity<String> addApplicant(@RequestBody ApplyLoanDTO loanDto) {

		logger.info("Received loan application: {}", loanDto);

		String msg = loanService.addApplicant(loanDto);

		String message = "Loan application received for customer ID: " + loanDto.getCustomerId();
		return ResponseEntity.ok(msg);
	}

	@PatchMapping("/requirement/{customerId}/{loanType}")
	public ResponseEntity<String> completeApplication(
	    @PathVariable Integer customerId,
	    @PathVariable LoanType loanType,
	    @RequestBody LoanApplicationDto loanApplicationDto) {

	    logger.info("Received loan application: {}", loanApplicationDto);
	    String msg = loanService.completeApplication(customerId,loanType, loanApplicationDto);
	    return ResponseEntity.ok(msg);
	}

	@PutMapping("/{loanApplicationId}/{loanType}")
	public ResponseEntity<String> updateLoanApplication(
	    @PathVariable Integer loanApplicationId,
	    @PathVariable LoanType loanType,
	    @RequestBody LoanApplicationDto loanApplicationDto) {

	    logger.info("Updating loan application with ID: {}", loanApplicationId);
	    String result = loanService.updateLoanApplication(loanApplicationId, loanType,loanApplicationDto);
	    return ResponseEntity.ok(result);
	}


	@DeleteMapping("/{loanApplicationId}")
	public ResponseEntity<String> deleteLoanApplication(@PathVariable Integer loanApplicationId) {
		logger.info("Deleting loan application with ID: {}", loanApplicationId);
		String result = loanService.deleteLoanApplication(loanApplicationId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{loanApplicationId}")
	public ResponseEntity<LoanApplication> getLoanApplicationById(@PathVariable Integer loanApplicationId) {
		logger.info("Fetching loan application with ID: {}", loanApplicationId);
		LoanApplication loanApp = loanService.getLoanApplicationById(loanApplicationId);
		if (loanApp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(loanApp);
	}

	@GetMapping
	public ResponseEntity<List<LoanApplication>> getAllLoanApplications() {
		logger.info("Fetching all loan applications");
		List<LoanApplication> allLoans = loanService.getAllLoanApplications();
		return ResponseEntity.ok(allLoans);
	}
	@GetMapping("/disburse/{customerId}")
	public ResponseEntity<DisburseDataDto> getData(@PathVariable Integer customerId)
	{
		 DisburseDataDto disburseData = loanService.getDisburseData(customerId);
		
		
		return new ResponseEntity<DisburseDataDto>(disburseData,HttpStatus.OK);
		
	}

}
