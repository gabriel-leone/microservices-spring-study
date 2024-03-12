package com.gabrielleone.loans.models.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoanDTO {
    @NotEmpty(message = "First Name can not be a null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "LoanType can not be a null or empty")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @NotNull(message = "")
    private float totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @NotNull(message = "")
    private float amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @NotNull(message = "")
    private float outstandingAmount;
}
