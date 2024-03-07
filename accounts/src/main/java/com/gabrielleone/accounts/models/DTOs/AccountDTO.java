package com.gabrielleone.accounts.models.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDTO {
    @NotNull(message = "Account number is required")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits long and contain only numbers. Ex.: 1234567890")
    private Long accountNumber;
    @NotEmpty(message = "Account type is required")
    private String accountType;
    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
