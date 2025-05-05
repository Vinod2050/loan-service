package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.GuarantorDetails;
import com.example.entity.LoanApplication;


@Repository
public interface GuarantorDetailsRepository extends JpaRepository<GuarantorDetails, Integer>{

	GuarantorDetails findByLoanApplication(LoanApplication loanApplication);

}
