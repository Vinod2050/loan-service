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
	public PropertyDetailsDto editPropertyDetails(Integer loanApplicationId, PropertyDetailsDto propertyDetailsDto) {
		System.out.println("Received PropertyDetailsDto for update: " + propertyDetailsDto);

		if (loanApplicationId == null || propertyDetailsDto == null) {
			System.out.println("Invalid input: loanApplicationId or propertyDetailsDto is null");
			return null;
		}

		PropertyDetails existingProperty = propertyDetailsRepository.findById(loanApplicationId).orElse(null);

		if (existingProperty == null) {
			System.out.println("No property found with id: " + loanApplicationId);
			return null;
		}

		// Update fields individually to ensure safe mapping
		if (propertyDetailsDto.getApprovalAuthority() != null)
			existingProperty.setApprovalAuthority(propertyDetailsDto.getApprovalAuthority());

		if (propertyDetailsDto.getAreaName() != null)
			existingProperty.setAreaName(propertyDetailsDto.getAreaName());

		if (propertyDetailsDto.getBuilderName() != null)
			existingProperty.setBuilderName(propertyDetailsDto.getBuilderName());

		if (propertyDetailsDto.getCarpetArea() != null)
			existingProperty.setCarpetArea(propertyDetailsDto.getCarpetArea());

		if (propertyDetailsDto.getCity() != null)
			existingProperty.setCity(propertyDetailsDto.getCity());

		if (propertyDetailsDto.getConstructionStatus() != null)
			existingProperty.setConstructionStatus(propertyDetailsDto.getConstructionStatus());

		if (propertyDetailsDto.getDistrict() != null)
			existingProperty.setDistrict(propertyDetailsDto.getDistrict());

		if (propertyDetailsDto.getIsUnderDispute() != null)
			existingProperty.setIsUnderDispute(propertyDetailsDto.getIsUnderDispute());

		if (propertyDetailsDto.getLandSurveyNumber() != null)
			existingProperty.setLandSurveyNumber(propertyDetailsDto.getLandSurveyNumber());

		if (propertyDetailsDto.getOwnershipType() != null)
			existingProperty.setOwnershipType(propertyDetailsDto.getOwnershipType());

		if (propertyDetailsDto.getPincode() != null)
			existingProperty.setPincode(propertyDetailsDto.getPincode());

		if (propertyDetailsDto.getProjectName() != null)
			existingProperty.setProjectName(propertyDetailsDto.getProjectName());

		if (propertyDetailsDto.getPropertyAddress() != null)
			existingProperty.setPropertyAddress(propertyDetailsDto.getPropertyAddress());

		if (propertyDetailsDto.getPropertyRegistrationNumber() != null)
			existingProperty.setPropertyRegistrationNumber(propertyDetailsDto.getPropertyRegistrationNumber());

		if (propertyDetailsDto.getPropertyType() != null)
			existingProperty.setPropertyType(propertyDetailsDto.getPropertyType());

		if (propertyDetailsDto.getPropertyValue() != null)
			existingProperty.setPropertyValue(propertyDetailsDto.getPropertyValue());

		if (propertyDetailsDto.getRemarks() != null)
			existingProperty.setRemarks(propertyDetailsDto.getRemarks());

		if (propertyDetailsDto.getState() != null)
			existingProperty.setState(propertyDetailsDto.getState());

		if (propertyDetailsDto.getConstructionPercentage() != null)
			existingProperty.setConstructionPercentage(propertyDetailsDto.getConstructionPercentage());
		
		if (propertyDetailsDto.getCustomerId() != null)
			existingProperty.setCustomerId(propertyDetailsDto.getCustomerId());

		System.out.println("Updated Property: " + existingProperty);

		PropertyDetails saved = propertyDetailsRepository.save(existingProperty);

		System.out.println("Saved PropertyDetails: " + saved);

		return modelMapper.map(saved, PropertyDetailsDto.class);
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
