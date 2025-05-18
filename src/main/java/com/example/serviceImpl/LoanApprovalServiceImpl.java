package com.example.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.LoanApprovalDto;
import com.example.dto.LoanSanctionDto;
import com.example.entity.LoanApplication;
import com.example.entity.LoanApproval;
import com.example.enums.LoanStatus;
import com.example.fiegnclient.SanctionFeignClient;
import com.example.repository.LoanApprovalRepository;
import com.example.repository.LoanRepository;
import com.example.service.LoanApprovalService;

@Service
public class LoanApprovalServiceImpl implements LoanApprovalService {

	@Autowired
	private LoanApprovalRepository loanApprovalRepository;
	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private SanctionFeignClient sanctionFeignClient;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addLoanStatus(LoanApprovalDto loanApprovalDto) {
		Optional<LoanApplication> optionalLoanApplication = loanRepository.findById(loanApprovalDto.getCustomerId());

		if (!optionalLoanApplication.isPresent()) {
			return "Loan application not found for customer ID: " + loanApprovalDto.getCustomerId();
		}

		LoanApplication loanApplication = optionalLoanApplication.get();

		Integer customerId = loanApprovalDto.getCustomerId();
		Optional<LoanApproval> optionalLoanApproval = loanApprovalRepository.findByCustomerId(customerId);

		LoanApproval loanApproval = optionalLoanApproval.orElse(new LoanApproval());

		loanApproval.setLoanApplication(loanApplication);

		if (loanApprovalDto.getCustomerId() != null)
			loanApproval.setCustomerId(loanApprovalDto.getCustomerId());
		if (loanApprovalDto.getIsAllPropertyDocumentVerified() != null)
			loanApproval.setIsAllPropertyDocumentVerified(loanApprovalDto.getIsAllPropertyDocumentVerified());
		if (loanApprovalDto.getIsIncomeProofVerified() != null)
			loanApproval.setIsIncomeProofVerified(loanApprovalDto.getIsIncomeProofVerified());
		if (loanApprovalDto.getIsGarantorVerified() != null)
			loanApproval.setIsGarantorVerified(loanApprovalDto.getIsGarantorVerified());
		if (loanApprovalDto.getIsCibilScoreGood() != null)
			loanApproval.setIsCibilScoreGood(loanApprovalDto.getIsCibilScoreGood());
		if (loanApprovalDto.getIsLoanStatusApproved() != null)
			loanApproval.setIsLoanStatusApproved(loanApprovalDto.getIsLoanStatusApproved());

		loanApprovalRepository.save(loanApproval);

		return "Loan status saved successfully.";
	}

	@Override
	public String loanSanction(Integer customerId) {

		LoanApproval existingApproval = loanApprovalRepository.findByCustomerId(customerId).get();

		LoanApplication existing = loanRepository.findByCustomerId(customerId);
		if (existingApproval == null) {
			return "LoanApproval not found for customer ID: " + customerId;
		}

		if (Boolean.TRUE.equals(existingApproval.getIsAllPropertyDocumentVerified())
				&& Boolean.TRUE.equals(existingApproval.getIsIncomeProofVerified())
				&& Boolean.TRUE.equals(existingApproval.getIsGarantorVerified())
				&& Boolean.TRUE.equals(existingApproval.getIsCibilScoreGood())) {

			existingApproval.setIsLoanStatusApproved(true);
			existing.setLoanStatus(LoanStatus.APPROVED);

			LoanSanctionDto loanSanctionDTO = new LoanSanctionDto();
			loanSanctionDTO.setFirstName(existing.getFirstName());
			loanSanctionDTO.setLastName(existing.getLastName());
			loanSanctionDTO.setCustomerEmail(existing.getCustomerEmail());
			loanSanctionDTO.setApplicationId(existing.getLoanApplicationId());
			loanSanctionDTO.setCustomerId(existing.getCustomerId());
			loanSanctionDTO.setCibilScore(existing.getCibilScore());
			loanSanctionDTO.setLoanType(existing.getLoanType());
			loanSanctionDTO.setMonthlyIncome(existing.getMonthlyIncome());
			loanSanctionDTO.setRequestedAmount(existing.getRequestedAmount());
			loanSanctionDTO.setInterestRate(existing.getIntrestRate());
			loanSanctionDTO.setRequestedTenure(existing.getRequestedTenure());

			sanctionFeignClient.createSanctionLetter(loanSanctionDTO);

			loanApprovalRepository.save(existingApproval);

			return "Loan sanctioned successfully.";
		} else {
			return "Loan cannot be sanctioned — criteria not met.";
		}
	}


}
