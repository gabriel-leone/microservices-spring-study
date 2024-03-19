package com.gabrielleone.cards.models.DTOs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record CardContactInfoDTO(String message, Map<String, String> contactDetails) {
}