package com.example.service;

import com.example.dto.GuarantorDetailsDto;

public interface GuarantorDetailsService {
 String addGuarantorDetails(Integer loanApplicationId, GuarantorDetailsDto guarantorDetailsDto);
	GuarantorDetailsDto getGuarantorDetails(Integer loanApplicationId);

	String updateGuarantorDetails(Integer loanApplicationId, GuarantorDetailsDto guarantorDetailsDto);

}
