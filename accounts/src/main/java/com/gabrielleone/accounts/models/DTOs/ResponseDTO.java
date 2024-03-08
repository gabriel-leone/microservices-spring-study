package com.gabrielleone.accounts.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold sucessful response information"
)
@Data @AllArgsConstructor
public class ResponseDTO {

    @Schema(
            name = "Status Code",
            description = "This is the status code of the response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            name = "Message",
            description = "This is the message of the response",
            example = "Success"
    )
    private String message;
}
