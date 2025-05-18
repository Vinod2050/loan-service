package com.example.service;

import com.example.dto.BankDetailsDto;
import com.example.entity.BankDetails;

public interface BankDetailsService {

	String addBankDetails(BankDetailsDto bankDetailsDto, Integer customerId);

	BankDetailsDto getDetails(Integer customerId);

}
