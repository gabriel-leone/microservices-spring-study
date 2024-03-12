package com.gabrielleone.loans.services;

import com.gabrielleone.loans.models.DTOs.LoanDTO;

public interface ILoanService {
    void createLoan(String email);

    LoanDTO fetchLoan(String email);

    boolean updateLoan(LoanDTO loansDto);

    boolean deleteLoan(String email);
}
