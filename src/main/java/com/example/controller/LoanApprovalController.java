package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoanApprovalDto;
import com.example.service.LoanApprovalService;

@RestController
@RequestMapping("/api/approvals")
public class LoanApprovalController {

	@Autowired
	private LoanApprovalService loanApprovalService;

	@PutMapping("/loan-status")
	public ResponseEntity<String> addLoanStatus(@RequestBody LoanApprovalDto loanApprovalDto) {

		String msg = loanApprovalService.addLoanStatus(loanApprovalDto);

		return ResponseEntity.ok(msg);
	}

	@PostMapping("/loan-sanction/{customerId}")
	public ResponseEntity<String> loanSanction(Integer customerId) {
		String msg = loanApprovalService.loanSanction(customerId);
		return ResponseEntity.ok(msg);

	}

	
}
