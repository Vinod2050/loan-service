package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PropertyDetailsDto;
import com.example.entity.LoanApplication;
import com.example.entity.PropertyDetails;
import com.example.repository.LoanRepository;
import com.example.repository.PropertyDetailsRepository;
import com.example.service.PropertyDetailsService;

@Service
public class PropertyDetailsServiceImpl implements PropertyDetailsService {

	@Autowired
	private PropertyDetailsRepository propertyDetailsRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PropertyDetailsDto savePropertyDetails(PropertyDetailsDto propertyDetailsDto, Integer loanApplicationId) {
	    
		Optional<LoanApplication> optionalLoanApp = loanRepository.findById(loanApplicationId);

		if (optionalLoanApp.isPresent()) {
		    LoanApplication loanApp = optionalLoanApp.get();
		    PropertyDetails propertyDetails = modelMapper.map(propertyDetailsDto, PropertyDetails.class);
		    propertyDetails.setLoanApplication(loanApp);
		    PropertyDetails saved = propertyDetailsRepository.save(propertyDetails);
		    return modelMapper.map(saved, PropertyDetailsDto.class);
		} else {
		    return null;
		}

	}




	@Override
	public List<PropertyDetailsDto> getAllPropertyDetails() {
		List<PropertyDetails> properties = propertyDetailsRepository.findAll();
		List<PropertyDetailsDto> dtoList = new ArrayList<>();
		for (PropertyDetails property : properties) {
			PropertyDetailsDto dto = modelMapper.map(property, PropertyDetailsDto.class);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public PropertyDetailsDto getPropertyDetailsById(Integer loanApplicationid) {
		PropertyDetails propertyDetails = propertyDetailsRepository.findById(loanApplicationid).get();
		return modelMapper.map(propertyDetails, PropertyDetailsDto.class);
	}

	@Override
	public PropertyDetailsDto editPropertyDetails(Integer loanApplicationid, PropertyDetailsDto propertyDetailsDto) {
		System.out.println("Received PropertyDetailsDto for update: " + propertyDetailsDto);

		PropertyDetails existingProperty = propertyDetailsRepository.findById(loanApplicationid).orElse(null);

		if (existingProperty != null) {
			System.out.println("Existing Property before update: " + existingProperty);

			existingProperty.setApprovalAuthority(propertyDetailsDto.getApprovalAuthority());
			existingProperty.setAreaName(propertyDetailsDto.getAreaName());
			existingProperty.setBuilderName(propertyDetailsDto.getBuilderName());
			existingProperty.setCarpetArea(propertyDetailsDto.getCarpetArea());
			existingProperty.setCity(propertyDetailsDto.getCity());
			existingProperty.setConstructionStatus(propertyDetailsDto.getConstructionStatus());
			existingProperty.setDistrict(propertyDetailsDto.getDistrict());
			existingProperty.setIsUnderDispute(propertyDetailsDto.getIsUnderDispute());
			existingProperty.setLandSurveyNumber(propertyDetailsDto.getLandSurveyNumber());
			existingProperty.setOwnershipType(propertyDetailsDto.getOwnershipType());
			existingProperty.setPincode(propertyDetailsDto.getPincode());
			existingProperty.setProjectName(propertyDetailsDto.getProjectName());
			existingProperty.setPropertyAddress(propertyDetailsDto.getPropertyAddress());
			existingProperty.setPropertyRegistrationNumber(propertyDetailsDto.getPropertyRegistrationNumber());
			existingProperty.setPropertyType(propertyDetailsDto.getPropertyType());
			existingProperty.setPropertyValue(propertyDetailsDto.getPropertyValue());
			existingProperty.setRemarks(propertyDetailsDto.getRemarks());
			existingProperty.setState(propertyDetailsDto.getState());

			System.out.println("Updated Property: " + existingProperty);

			PropertyDetails saved = propertyDetailsRepository.save(existingProperty);

			System.out.println("Saved PropertyDetails: " + saved);

			return modelMapper.map(saved, PropertyDetailsDto.class);
		} else {
			System.out.println("No property found with id: " + loanApplicationid);
			return propertyDetailsDto;
		}
	}

	@Override
	public void deletePropertyDetails(Integer loanApplicationid) {
		PropertyDetails existingProperty = propertyDetailsRepository.findById(loanApplicationid).orElse(null);

		if (existingProperty != null) {
			propertyDetailsRepository.deleteById(loanApplicationid);
		} else {
			throw new IllegalArgumentException("Property not found for ID: " + loanApplicationid);
		}
	}

}
