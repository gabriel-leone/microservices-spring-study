package com.gabrielleone.loans.models.mappers;

import com.gabrielleone.loans.models.DTOs.LoanDTO;
import com.gabrielleone.loans.models.entities.Loan;

public class LoanMapper {
    public static LoanDTO mapToLoanDTO(Loan loan, LoanDTO loanDTO) {
        loanDTO.setLoanNumber(loan.getLoanNumber());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setTotalLoan(loan.getTotalLoan());
        loanDTO.setEmail(loan.getEmail());
        loanDTO.setAmountPaid(loan.getAmountPaid());
        loanDTO.setOutstandingAmount(loan.getOutstandingAmount());
        return loanDTO;
    }

    public static Loan mapToLoan(LoanDTO loanDTO, Loan loan) {
        loan.setLoanNumber(loanDTO.getLoanNumber());
        loan.setLoanType(loanDTO.getLoanType());
        loan.setTotalLoan(loanDTO.getTotalLoan());
        loan.setEmail(loanDTO.getEmail());
        loan.setAmountPaid(loanDTO.getAmountPaid());
        loan.setOutstandingAmount(loanDTO.getOutstandingAmount());
        return loan;
    }
}
