package com.gabrielleone.accounts.models.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfoDTO {
    private String message;
    private Map<String, String> contactDetails;
}
