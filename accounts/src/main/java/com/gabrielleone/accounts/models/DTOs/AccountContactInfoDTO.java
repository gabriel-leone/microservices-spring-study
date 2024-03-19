package com.gabrielleone.accounts.models.DTOs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDTO(String message, Map<String, String> contactDetails) {
}
