package com.gabrielleone.accounts.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDTO {
    private String statusCode;
    private String message;
}
