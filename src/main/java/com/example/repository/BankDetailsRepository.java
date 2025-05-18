package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.BankDetailsDto;
import com.example.entity.BankDetails;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Integer> {

	BankDetails findByCustomerId(Integer customerId);
    

}
