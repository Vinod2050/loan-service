package com.example.dto;

import lombok.Data;

@Data
public class GuarantorDetailsDto {

	private String guarantorName;
	private String guarantorDateOfBirth;
	private String guarantorRelationshipWithCustomer;
	private Long guarantorMobileNumber;
	private Long guarantorAdharCardNo;
	private String guarantorMortgageDetails;
	private String guarantorJobDetails;
	private String guarantorLocalAddress;
	private String guarantorPermanentAddress;

}
