package com.gabrielleone.accounts.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDTO {
    @Schema(
            name = "API Path",
            description = "This is the path of the API that caused the error",
            example = "/api/createAccount"
    )
    private String apiPath;

    @Schema(
            name = "Error Code",
            description = "This is the error code of the response",
            example = "BAD_REQUEST"
    )
    private HttpStatus errorCode;

    @Schema(
            name = "Error Message",
            description = "This is the error message of the response",
            example = "Cannot create account for customer with email:"
    )
    private String errorMessage;

    @Schema(
            name = "Error Time",
            description = "This is the time of the error",
            example = "2021-08-01T12:00:00"
    )
    private LocalDateTime errorTime;
}
