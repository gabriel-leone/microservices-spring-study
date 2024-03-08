package com.gabrielleone.accounts.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "This is the data transfer object for the account entity"
)
@Data
public class AccountDTO {
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
