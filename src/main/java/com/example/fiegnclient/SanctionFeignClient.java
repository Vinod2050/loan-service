package com.example.fiegnclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dto.LoanSanctionDto;

@FeignClient(name="sanction-service")
public interface SanctionFeignClient {
	
	@PostMapping("/api/sanctions/sanctionLetters")
	public ResponseEntity<String> createSanctionLetter(@RequestBody LoanSanctionDto loanSanctionDto);


	

}