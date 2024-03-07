package com.gabrielleone.accounts.models.DTOs;

import lombok.Data;

@Data
public class CustomerDetailsDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private String accountType;
    private Long accountNumber;
    private String branchAddress;
}
