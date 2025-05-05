package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PropertyDetailsDto;
import com.example.service.PropertyDetailsService;

@RestController
@RequestMapping("/api/properties/")
public class PropertyDetailsController {

	@Autowired
	private PropertyDetailsService propertyDetailsService;

	@PostMapping("/{loanApplicationId}")
    public ResponseEntity<PropertyDetailsDto> createPropertyDetails(@RequestBody PropertyDetailsDto propertyDetails,@PathVariable Integer loanApplicationId) {
        PropertyDetailsDto savedProperty = propertyDetailsService.savePropertyDetails(propertyDetails,loanApplicationId);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);  
    }

	@GetMapping
	public ResponseEntity<List<PropertyDetailsDto>> getAllPropertyDetails() {
		List<PropertyDetailsDto> properties = propertyDetailsService.getAllPropertyDetails();
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	@GetMapping("/{loanApplicationid}")
	public ResponseEntity<PropertyDetailsDto> getPropertyDetailsById(@PathVariable Integer loanApplicationid) {
		PropertyDetailsDto property = propertyDetailsService.getPropertyDetailsById(loanApplicationid);
		return new ResponseEntity<PropertyDetailsDto>(property,HttpStatus.OK);
	}

	@PatchMapping("/{loanApplicationid}")
	public ResponseEntity<PropertyDetailsDto> editPropertyDetails(@PathVariable Integer loanApplicationid,
			@RequestBody PropertyDetailsDto propertyDetails) {
		PropertyDetailsDto details = propertyDetailsService.editPropertyDetails(loanApplicationid, propertyDetails);
		if (details != null) {
			return new ResponseEntity<>(details, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{loanApplicationid}")
	public ResponseEntity<Void> deletePropertyDetails(@PathVariable Integer loanApplicationid) {
		try {
			propertyDetailsService.deletePropertyDetails(loanApplicationid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
	}
}
