package com.gabrielleone.accounts.models.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDetailsDTO {
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 60, message = "Name must be between 2 and 60 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "^$|[0-9]{11}", message = "Mobile number must be 11 digits long and contain only numbers. Ex.: 11912345678")
    private String mobileNumber;

    @NotNull(message = "Account number is required")
//    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits long and contain only numbers. Ex.: 1234567890")
    private Long accountNumber;

    @NotEmpty(message = "Account type is required")
    private String accountType;

    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
