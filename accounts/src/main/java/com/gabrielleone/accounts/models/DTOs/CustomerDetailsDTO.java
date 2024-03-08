package com.gabrielleone.accounts.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(
        name = "CustomerDetails",
        description = "This is the data transfer object for the customer details"
)
@Data
public class CustomerDetailsDTO {
    @Schema(
            name = "Name",
            description = "This is the name of the customer",
            example = "John Doe"
    )
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 60, message = "Name must be between 2 and 60 characters")
    private String name;

    @Schema(
            name = "Email",
            description = "This is the email of the customer",
            example = "john.doe@gmail.com"
    )
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @Schema(
            name = "Mobile Number",
            description = "This is the mobile number of the customer",
            example = "11912345678"
    )
    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "^$|[0-9]{11}", message = "Mobile number must be 11 digits long and contain only numbers. Ex.: 11912345678")
    private String mobileNumber;

    @Schema(
            name = "Account Number",
            description = "This is the account number of the customer",
            example = "1234567890"
    )
    @NotNull(message = "Account number is required")
//    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits long and contain only numbers. Ex.: 1234567890")
    private Long accountNumber;

    @Schema(
            name = "Account Type",
            description = "This is the account type of the customer",
            example = "Savings"
    )
    @NotEmpty(message = "Account type is required")
    private String accountType;

    @Schema(
            name = "Branch Address",
            description = "This is the branch address of the customer",
            example = "123 Main St, Springfield, IL 62701"
    )
    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
