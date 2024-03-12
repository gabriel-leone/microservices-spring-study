package com.gabrielleone.loans.repositories;

import com.gabrielleone.loans.models.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILoanRepository extends JpaRepository<Loan, Long>{
    Optional<Loan> findByEmail(String email);
}
