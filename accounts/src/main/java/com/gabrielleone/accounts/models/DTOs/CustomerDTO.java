package com.gabrielleone.accounts.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "This is the data transfer object for the customer entity"
)
public class CustomerDTO {
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
}
