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

	    return loanRepository.findById(loanApplicationId).map(loanApplication -> {

	        GuarantorDetails existingGuarantor = guarantorDetailsRepository.findByLoanApplication(loanApplication);

	        if (existingGuarantor == null) {
	            GuarantorDetails guarantorDetails = modelmapper.map(guarantorDetailsDto, GuarantorDetails.class);
	            guarantorDetails.setLoanApplication(loanApplication);
	            guarantorDetailsRepository.save(guarantorDetails);
	            return "Guarantor details added successfully for ID: " + loanApplicationId;
	        } else {
	            return "Guarantor details already exist for ID: " + loanApplicationId;
	        }

	    }).orElse("Loan Application not found for ID: " + loanApplicationId);
	}


	@Override
	public GuarantorDetailsDto getGuarantorDetails(Integer loanApplicationId) {

		if (loanRepository.existsById(loanApplicationId)) {

			LoanApplication loanApplication = loanRepository.findById(loanApplicationId).get();

			GuarantorDetails guarantorDetails = guarantorDetailsRepository.findByLoanApplication(loanApplication);

			if (guarantorDetails != null) {

				GuarantorDetailsDto guarantorDetailsDto = modelmapper.map(guarantorDetails, GuarantorDetailsDto.class);

				return guarantorDetailsDto;
			}
			return null;
		}
		return null;
	}



	@Override
	public String updateGuarantorDetails(Integer loanApplicationId, GuarantorDetailsDto guarantorDetailsDto) {

		if (loanRepository.existsById(loanApplicationId)) {

			LoanApplication loanApplication = loanRepository.findById(loanApplicationId).get();

			GuarantorDetails guarantorDetails = guarantorDetailsRepository.findByLoanApplication(loanApplication);

			if (guarantorDetails != null && guarantorDetailsDto != null) {

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
					guarantorDetails.setGuarantorRelationshipWithCustomer(
							guarantorDetailsDto.getGuarantorRelationshipWithCustomer());
				}
				guarantorDetailsRepository.save(guarantorDetails);
				return "Guarantor Details data updated successfully for loan application id : " + loanApplicationId;
			}
			return "Either Guarantor details is not exists/update data is not present for loan application id : " + loanApplicationId;
		}
		return "loan application id is not exist for loan application id : " + loanApplicationId;
	}
}
