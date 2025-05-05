package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.GuarantorDetailsDto;
import com.example.service.GuarantorDetailsService;

@RestController(value = "/api/guarantors")
public class GuarantorDetailsController {

	@Autowired
	private GuarantorDetailsService guarantorDetailsService;

	@PostMapping("/{loanApplicationId}")
	private ResponseEntity<String> addGuarantorDetails(@PathVariable Integer loanApplicationId, @RequestBody GuarantorDetailsDto guarantorDetailsDto) {
		String message = guarantorDetailsService.addGuarantorDetails(loanApplicationId,guarantorDetailsDto);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/{loanApplicationId}")
	private ResponseEntity<GuarantorDetailsDto> getGuarantorDetails(@PathVariable Integer loanApplicationId) {
		GuarantorDetailsDto guarantorDetailsDto = guarantorDetailsService.getGuarantorDetails(loanApplicationId);
		if (guarantorDetailsDto != null) {
			return new ResponseEntity<GuarantorDetailsDto>(guarantorDetailsDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<GuarantorDetailsDto>(HttpStatus.NO_CONTENT);
		}
	}


	@PatchMapping("/{loanApplicationId}")
	private ResponseEntity<String> updateGuarantorDetails(@PathVariable Integer loanApplicationId,
			@RequestBody GuarantorDetailsDto guarantorDetailsDto) {
		String message = guarantorDetailsService.updateGuarantorDetails(loanApplicationId, guarantorDetailsDto);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
