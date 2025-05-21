package com.example.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.GuarantorDetailsDto;
import com.example.entity.GuarantorDetails;
import com.example.entity.LoanApplication;
import com.example.repository.GuarantorDetailsRepository;
import com.example.repository.LoanRepository;
import com.example.service.GuarantorDetailsService;

@Service
public class GuarantorDetailsServiceImpl implements GuarantorDetailsService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public String addGuarantorDetails(Integer loanApplicationId, GuarantorDetailsDto guarantorDetailsDto) {
		if (guarantorDetailsDto == null) {
			return "Invalid request: Guarantor details cannot be null.";
		}

		LoanApplication loanApplication = loanRepository.findById(loanApplicationId).orElse(null);
		if (loanApplication == null) {
			return "Loan Application not found for ID: " + loanApplicationId;
		}

		GuarantorDetails existingGuarantor = guarantorDetailsRepository.findByLoanApplication(loanApplication);
		if (existingGuarantor != null) {
			// Update existing guarantor
			existingGuarantor.setGuarantorName(guarantorDetailsDto.getGuarantorName());
			existingGuarantor.setGuarantorDateOfBirth(guarantorDetailsDto.getGuarantorDateOfBirth());
			existingGuarantor.setGuarantorRelationshipWithCustomer(guarantorDetailsDto.getGuarantorRelationshipWithCustomer());
			existingGuarantor.setGuarantorMobileNumber(guarantorDetailsDto.getGuarantorMobileNumber());
			existingGuarantor.setGuarantorAdharCardNo(guarantorDetailsDto.getGuarantorAdharCardNo());
			existingGuarantor.setGuarantorMortgageDetails(guarantorDetailsDto.getGuarantorMortgageDetails());
			existingGuarantor.setGuarantorJobDetails(guarantorDetailsDto.getGuarantorJobDetails());
			existingGuarantor.setGuarantorLocalAddress(guarantorDetailsDto.getGuarantorLocalAddress());
			existingGuarantor.setGuarantorPermanentAddress(guarantorDetailsDto.getGuarantorPermanentAddress());
			// already associated with loanApplication
			guarantorDetailsRepository.save(existingGuarantor);
			return "Guarantor details updated successfully for ID: " + loanApplicationId;
		} else {
			// Create new guarantor
			GuarantorDetails newGuarantor = modelmapper.map(guarantorDetailsDto, GuarantorDetails.class);
			newGuarantor.setLoanApplication(loanApplication);
			guarantorDetailsRepository.save(newGuarantor);
			return "Guarantor details added successfully for ID: " + loanApplicationId;
		}
	}

	 @Override
	    public String updateGuarantorDetails(Integer loanApplicationId, GuarantorDetailsDto guarantorDetailsDto) {
	        if (guarantorDetailsDto == null) {
	            return "Invalid update data: Guarantor details DTO is null.";
	        }

	        return loanRepository.findById(loanApplicationId).map(loanApplication -> {
	            GuarantorDetails guarantorDetails = guarantorDetailsRepository.findByLoanApplication(loanApplication);

	            if (guarantorDetails == null) {
	                return "Guarantor details do not exist for loan application ID: " + loanApplicationId;
	            }

	            if (guarantorDetailsDto.getGuarantorAdharCardNo() != null) {
	                guarantorDetails.setGuarantorAdharCardNo(guarantorDetailsDto.getGuarantorAdharCardNo());
	            }

	            if (guarantorDetailsDto.getGuarantorDateOfBirth() != null) {
	                guarantorDetails.setGuarantorDateOfBirth(guarantorDetailsDto.getGuarantorDateOfBirth());
	            }

	            if (guarantorDetailsDto.getGuarantorJobDetails() != null) {
	                guarantorDetails.setGuarantorJobDetails(guarantorDetailsDto.getGuarantorJobDetails());
	            }

	            if (guarantorDetailsDto.getGuarantorLocalAddress() != null) {
	                guarantorDetails.setGuarantorLocalAddress(guarantorDetailsDto.getGuarantorLocalAddress());
	            }

	            if (guarantorDetailsDto.getGuarantorMobileNumber() != null) {
	                guarantorDetails.setGuarantorMobileNumber(guarantorDetailsDto.getGuarantorMobileNumber());
	            }

	            if (guarantorDetailsDto.getGuarantorMortgageDetails() != null) {
	                guarantorDetails.setGuarantorMortgageDetails(guarantorDetailsDto.getGuarantorMortgageDetails());
	            }

	            if (guarantorDetailsDto.getGuarantorName() != null) {
	                guarantorDetails.setGuarantorName(guarantorDetailsDto.getGuarantorName());
	            }

	            if (guarantorDetailsDto.getGuarantorPermanentAddress() != null) {
	                guarantorDetails.setGuarantorPermanentAddress(guarantorDetailsDto.getGuarantorPermanentAddress());
	            }

	            if (guarantorDetailsDto.getGuarantorRelationshipWithCustomer() != null) {
	                guarantorDetails.setGuarantorRelationshipWithCustomer(guarantorDetailsDto.getGuarantorRelationshipWithCustomer());
	            }

	            guarantorDetailsRepository.save(guarantorDetails);
	            return "Guarantor details updated successfully for loan application ID: " + loanApplicationId;

	        }).orElse("Loan application ID does not exist: " + loanApplicationId);
	    }
	
	

	@Override
	public GuarantorDetailsDto getGuarantorDetails(Integer loanApplicationId) {
		// TODO Auto-generated method stub
		return null;
	}
}
