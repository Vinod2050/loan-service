package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.LoanApplication;
import com.example.entity.LoanApproval;

public interface LoanApprovalRepository extends JpaRepository<LoanApproval, Integer>{


	Optional<LoanApproval> findByCustomerId(Integer customerId);

}
