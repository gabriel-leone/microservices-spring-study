package com.gabrielleone.accounts.models.mapper;

import com.gabrielleone.accounts.models.DTOs.CustomerDetailsDTO;
import com.gabrielleone.accounts.models.entities.Account;
import com.gabrielleone.accounts.models.entities.Customer;

public class CustomerDetailsMapper {
    public static CustomerDetailsDTO mapToCustomerDetailsDTO(Customer customer, Account account, CustomerDetailsDTO customerDetailsDTO) {
        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setEmail(customer.getEmail());
        customerDetailsDTO.setMobileNumber(customer.getMobileNumber());
        customerDetailsDTO.setAccountType(account.getAccountType());
        customerDetailsDTO.setAccountNumber(account.getAccountNumber());
        customerDetailsDTO.setBranchAddress(account.getBranchAddress());
        return customerDetailsDTO;
    }

    public static Customer mapToCustomer(CustomerDetailsDTO customerDetailsDTO, Customer customer) {
        customer.setName(customerDetailsDTO.getName());
        customer.setEmail(customerDetailsDTO.getEmail());
        customer.setMobileNumber(customerDetailsDTO.getMobileNumber());
        return customer;
    }

    public static Account mapToAccount(CustomerDetailsDTO customerDetailsDTO, Account account) {
        account.setAccountType(customerDetailsDTO.getAccountType());
        account.setBranchAddress(customerDetailsDTO.getBranchAddress());
        return account;
    }
}
