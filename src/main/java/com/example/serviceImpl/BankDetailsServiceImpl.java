package com.example.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BankDetailsDto;
import com.example.entity.BankDetails;
import com.example.entity.LoanApplication;
import com.example.repository.BankDetailsRepository;
import com.example.repository.LoanRepository;
import com.example.service.BankDetailsService;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addBankDetails(BankDetailsDto bankDetailsDto, Integer customerId) {
	    LoanApplication application = loanRepository.findByCustomerId(customerId);
	    if (application == null) {
	        return "Customer Id Not Found In Loan Applications..!";
	    }

	    BankDetails existingDetails = bankDetailsRepository.findByCustomerId(application.getCustomerId());
	    if (existingDetails != null) {
	        return "Bank Details Already Exist";
	    }

	    BankDetails bankDetails = modelMapper.map(bankDetailsDto, BankDetails.class);
	    bankDetails.setLoanApplication(application);
	    bankDetails.setCustomerId(customerId);
	    bankDetailsRepository.save(bankDetails);
	    return "Data Stored Successfully..!";
	}


	@Override
	public BankDetailsDto getDetails(Integer customerId) {
		if (customerId != null) {
			BankDetails details = bankDetailsRepository.findByCustomerId(customerId);
			BankDetailsDto bankDetail = modelMapper.map(details,BankDetailsDto.class);
			return bankDetail;
		}
		return null;

	}
}
