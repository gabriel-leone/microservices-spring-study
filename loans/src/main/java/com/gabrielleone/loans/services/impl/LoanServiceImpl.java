package com.gabrielleone.loans.services.impl;

import com.gabrielleone.loans.constants.LoanConstants;
import com.gabrielleone.loans.exceptions.ResourceNotFoundException;
import com.gabrielleone.loans.models.DTOs.LoanDTO;
import com.gabrielleone.loans.models.entities.Loan;
import com.gabrielleone.loans.models.mappers.LoanMapper;
import com.gabrielleone.loans.repositories.ILoanRepository;
import com.gabrielleone.loans.services.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private ILoanRepository loanRepository;

    @Override
    public void createLoan(String email) {
        loanRepository.findByEmail(email).ifPresent(c -> {
            throw new ResourceNotFoundException("Loan", "email", email);
        });
        loanRepository.save(createNewLoan(email));
    }

    private Loan createNewLoan(String email) {
        Loan newLoan = new Loan();
        newLoan.setEmail(email);
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoanDTO fetchLoan(String email) {
        Loan loan = loanRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "email", email)
        );
        return LoanMapper.mapToLoanDTO(loan, new LoanDTO());
    }

    @Override
    public boolean updateLoan(LoanDTO loansDto) {
        Loan loan = loanRepository.findByEmail(loansDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "email", loansDto.getEmail())
        );
        LoanMapper.mapToLoan(loansDto, loan);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String email) {
        Loan loan = loanRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "email", email)
        );
        loanRepository.delete(loan);
        return true;
    }

}
