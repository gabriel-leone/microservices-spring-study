package com.gabrielleone.loans.models.DTOs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record LoanContactInfoDTO(String message, Map<String, String> contactDetails) {
}
