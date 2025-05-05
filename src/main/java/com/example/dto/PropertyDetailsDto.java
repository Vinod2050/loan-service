package com.example.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.enums.ApprovalAuthority;
import com.example.enums.ConstructionStatus;
import com.example.enums.OwnershipType;
import com.example.enums.PropertyType;

import lombok.Data;

@Data
public class PropertyDetailsDto {
	

	@Enumerated(EnumType.STRING)
	private PropertyType propertyType; // e.g., Apartment, Villa, Plot

	@Enumerated(EnumType.STRING)
	private OwnershipType ownershipType; // e.g., Self-Owned, Joint, Rented

	private String propertyAddress;

	private String city;

	private String state;

	private String district;

	private String pincode;

	private String areaName;

	private Double propertyValue; // Market value of the property

	private Double carpetArea; // In square feet or meters

	@Enumerated(EnumType.STRING)
	private ConstructionStatus constructionStatus; // e.g., Under Construction, Ready to Move

	private String builderName;

	private String projectName;

	@Enumerated(EnumType.STRING)
	private ApprovalAuthority approvalAuthority; // e.g., RERA Approved, Local Authority

	private String propertyRegistrationNumber;

	private String landSurveyNumber;

	private Boolean isUnderDispute;

//	@Lob
//	private byte[] propertyDocument;

	private String remarks;

}
