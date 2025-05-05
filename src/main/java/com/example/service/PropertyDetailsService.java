package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.dto.PropertyDetailsDto;
import com.example.entity.PropertyDetails;

public interface PropertyDetailsService {

	PropertyDetailsDto savePropertyDetails(PropertyDetailsDto propertyDetails, Integer loanApplicationId);

	List<PropertyDetailsDto> getAllPropertyDetails();

	PropertyDetailsDto getPropertyDetailsById(Integer id);

	PropertyDetailsDto editPropertyDetails(Integer id, PropertyDetailsDto propertyDetails);

	void deletePropertyDetails(Integer id);

}
