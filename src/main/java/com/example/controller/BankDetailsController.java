package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BankDetailsDto;
import com.example.entity.BankDetails;
import com.example.service.BankDetailsService;

@RestController
public class BankDetailsController {

	@Autowired
	private BankDetailsService bankDetailsService;
	
	@PostMapping(value = "/addBankDetails/{customerId}")
	public ResponseEntity<String>addBankDetails(@RequestBody  BankDetailsDto bankDetailsDto,@PathVariable Integer customerId)
	{
		String msg =	bankDetailsService.addBankDetails(bankDetailsDto,customerId);
		return new ResponseEntity<String>(msg,HttpStatus.OK);	
	}
	
	@GetMapping(value = "/{customerId}")
	public ResponseEntity<BankDetailsDto> getBankDetails(@PathVariable Integer customerId)
	{
		BankDetailsDto details=	bankDetailsService.getDetails(customerId);
	return new ResponseEntity<BankDetailsDto>(details,HttpStatus.OK);
	}
}
