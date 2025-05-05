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
import com.example.dto.LoanApplicationDto;
import com.example.entity.LoanApplication;
import com.example.enums.LoanStatus;
import com.example.repository.LoanRepository;
import com.example.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addApplicant(ApplyLoanDTO loanDto) {

		LoanApplication entity = modelMapper.map(loanDto, LoanApplication.class);

		loanRepository.save(entity);

		return "Loan application submitted successfully.";
	}

	@Override
	public String completeApplication(Integer customerId, LoanApplicationDto loanApplicationDto) {
		LoanApplication existing = loanRepository.findByCustomerId(customerId);

		if (existing == null) {
			throw new RuntimeException("Loan application not found for customerId: " + customerId);
		}

		modelMapper.map(loanApplicationDto, existing);
		 existing.setLoanStatus(LoanStatus.PENDING);  
		    existing.setApplicationDate(LocalDate.now());  
		loanRepository.save(existing);

		return "Loan Application Updated Successfully";
	}

	@Override
	public String updateLoanApplication(Integer id, LoanApplicationDto dto) {
		Optional<LoanApplication> opt = loanRepository.findById(id);
		if (opt.isEmpty()) {
			return "Loan Application Not Found";
		}

		LoanApplication application = opt.get();
		modelMapper.map(dto, application);
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

	

}
