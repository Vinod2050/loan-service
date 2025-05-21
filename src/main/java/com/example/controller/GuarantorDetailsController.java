package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.GuarantorDetailsDto;
import com.example.service.GuarantorDetailsService;

@RestController
@RequestMapping("/api/guarantors")
public class GuarantorDetailsController {

	@Autowired
	private GuarantorDetailsService guarantorDetailsService;

	@PostMapping("/{loanApplicationId}")
	public ResponseEntity<String> addGuarantorDetails(@PathVariable Integer loanApplicationId,
			@Valid @RequestBody GuarantorDetailsDto guarantorDetailsDto) {
		String message = guarantorDetailsService.addGuarantorDetails(loanApplicationId, guarantorDetailsDto);
		return ResponseEntity.ok(message);
	}

	@GetMapping("/{loanApplicationId}")
	public ResponseEntity<GuarantorDetailsDto> getGuarantorDetails(@PathVariable Integer loanApplicationId) {
		GuarantorDetailsDto dto = guarantorDetailsService.getGuarantorDetails(loanApplicationId);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PatchMapping("/{loanApplicationId}")
	public ResponseEntity<String> updateGuarantorDetails(@PathVariable Integer loanApplicationId,
			@Valid @RequestBody GuarantorDetailsDto guarantorDetailsDto) {
		String message = guarantorDetailsService.updateGuarantorDetails(loanApplicationId, guarantorDetailsDto);
		return ResponseEntity.ok(message);
	}
}
