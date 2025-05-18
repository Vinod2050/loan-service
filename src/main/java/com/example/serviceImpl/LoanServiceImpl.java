package com.example.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ApplyLoanDTO;
import com.example.dto.DisburseDataDto;
import com.example.dto.LoanApplicationDto;
import com.example.dto.LoanSanctionDto;
import com.example.entity.BankDetails;
import com.example.entity.LoanApplication;
import com.example.entity.PropertyDetails;
import com.example.enums.LoanStatus;
import com.example.enums.LoanType;
import com.example.fiegnclient.SanctionFeignClient;
import com.example.repository.BankDetailsRepository;
import com.example.repository.LoanRepository;
import com.example.repository.PropertyDetailsRepository;
import com.example.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	@Autowired
	private PropertyDetailsRepository propertyDetailsRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addApplicant(ApplyLoanDTO loanDto) {
		if (loanDto == null || loanDto.getCustomerId() == null) {
			return "Invalid loan application data.";
		}

		LoanApplication existing = loanRepository.findByCustomerId(loanDto.getCustomerId());

		if (existing != null) {
			if (loanDto.getFirstName() != null) {
				existing.setFirstName(loanDto.getFirstName());
			}
			if (loanDto.getLastName() != null) {
				existing.setLastName(loanDto.getLastName());
			}
			if (loanDto.getCibilScore() != null) {
				existing.setCibilScore(loanDto.getCibilScore());
			}
			if (loanDto.getCustomerEmail() != null) {
				existing.setCustomerEmail(loanDto.getCustomerEmail());
			}
			if (loanDto.getIsDocumentVerified() != null) {
				existing.setIsDocumentVerified(loanDto.getIsDocumentVerified());
			}
			if (loanDto.getIsCustometrVerified() != null) {
				existing.setIsCustometrVerified(loanDto.getIsCustometrVerified());
			}

			loanRepository.save(existing);
			return "Loan application updated successfully.";
		}

		LoanApplication newApp = modelMapper.map(loanDto, LoanApplication.class);
		loanRepository.save(newApp);
		return "Loan application submitted successfully.";

	}

	@Override
	public String completeApplication(Integer customerId, LoanType loanType, LoanApplicationDto loanApplicationDto) {

		LoanApplication existing = null;
		try {
			existing = loanRepository.findByCustomerId(customerId);
		} catch (Exception e) {
			System.out.println("Error fetching loan application: " + e.getMessage());
			return "Failed to fetch loan application.";
		}

		if (existing == null) {
			return "Loan application not found for customerId: " + customerId;
		}

		// Manual mapping from DTO to Entity
		try {
			if (loanApplicationDto.getRequestedAmount() != null) {
				existing.setRequestedAmount(loanApplicationDto.getRequestedAmount());
			}
			if (loanApplicationDto.getRequestedTenure() != null) {
				existing.setRequestedTenure(loanApplicationDto.getRequestedTenure());
			}
			if (loanApplicationDto.getIntrestRate() != null) {
				existing.setIntrestRate(loanApplicationDto.getIntrestRate());
			}
			if (loanApplicationDto.getFirstName() != null) {
				existing.setFirstName(loanApplicationDto.getFirstName());
			}
			if (loanApplicationDto.getLastName() != null) {
				existing.setLastName(loanApplicationDto.getLastName());
			}
			if (loanApplicationDto.getCustomerEmail() != null) {
				existing.setCustomerEmail(loanApplicationDto.getCustomerEmail());
			}
			if (loanApplicationDto.getMonthlyIncome() != null) {
				existing.setMonthlyIncome(loanApplicationDto.getMonthlyIncome());
			}
			if (loanApplicationDto.getDownPaymentAmount() != null) {
				existing.setDownPaymentAmount(loanApplicationDto.getDownPaymentAmount());
			}

			// Set loan-specific data
			existing.setLoanType(loanType);
			existing.setLoanStatus(LoanStatus.PENDING);
			existing.setApplicationDate(LocalDate.now());

		} catch (Exception e) {
			System.out.println("Error setting loan application fields: " + e.getMessage());
			return "Failed to update loan application.";
		}

		try {
			loanRepository.save(existing);
		} catch (Exception e) {
			System.out.println("Error saving loan application: " + e.getMessage());
			return "Loan application could not be saved.";
		}

		return "Loan Application Updated Successfully";
	}

	@Override
	public String updateLoanApplication(Integer id, LoanType loanType, LoanApplicationDto dto) {
		Optional<LoanApplication> opt = loanRepository.findById(id);

		if (opt.isEmpty()) {
			return "Loan Application Not Found";
		}

		LoanApplication application = opt.get();

		modelMapper.map(dto, application);

		application.setLoanType(loanType);

		application.setApplicationDate(LocalDate.now());

		loanRepository.save(application);

		return "Loan Application Updated Successfully";
	}

	@Override
	public String deleteLoanApplication(Integer id) {
		if (!loanRepository.existsById(id)) {
			return "Loan Application Not Found";
		}
		loanRepository.deleteById(id);
		return "Loan Application Deleted Successfully";
	}

	@Override
	public LoanApplication getLoanApplicationById(Integer loanApplicationId) {
		LoanApplication loanApplication = loanRepository.findById(loanApplicationId).get();
		return loanApplication;
	}

	@Override
	public List<LoanApplication> getAllLoanApplications() {
		return loanRepository.findAll();
	}

	@Override
	public DisburseDataDto getDisburseData(Integer customerId) {
		if (customerId == null) {
			throw new RuntimeException("Customer ID must not be null");
		}

		DisburseDataDto disburseDataDto = new DisburseDataDto();
		disburseDataDto.setCustomerId(customerId);

		// Fetch LoanApplication data
		LoanApplication loanApplication = loanRepository.findByCustomerId(customerId);
		if (loanApplication != null) {
			disburseDataDto.setDownPaymentAmount(loanApplication.getDownPaymentAmount());
		}

		// Fetch BankDetails data
		BankDetails bankDetails = bankDetailsRepository.findByCustomerId(customerId);
		if (bankDetails != null) {
			disburseDataDto.setBankName(bankDetails.getBankName());
			disburseDataDto.setAccountNumber(bankDetails.getAccountNumber());
			disburseDataDto.setIfscCode(bankDetails.getIfscCode());
			disburseDataDto.setAccountHolderName(bankDetails.getAccountHolderName());
		}

		// Fetch PropertyDetails data
		PropertyDetails propertyDetails = propertyDetailsRepository.findByCustomerId(customerId);
		if (propertyDetails != null) {
			disburseDataDto.setConstructionStatus(propertyDetails.getConstructionStatus());
			disburseDataDto.setConstructionPercentage(propertyDetails.getConstructionPercentage());
		}

		return disburseDataDto;
	}


}
