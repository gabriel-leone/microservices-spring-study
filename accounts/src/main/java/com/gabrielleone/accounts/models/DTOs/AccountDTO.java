package com.gabrielleone.accounts.models.DTOs;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
