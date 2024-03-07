package com.gabrielleone.accounts.services;

import com.gabrielleone.accounts.models.DTOs.CustomerDTO;
import com.gabrielleone.accounts.models.DTOs.CustomerDetailsDTO;

public interface IAccountService {

    /**
     * Create a new account
     * @param customerDTO CustomerDTO object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Get account details
     * @param customerEmail Customer email
     * @return CustomerDTO object
     */
    CustomerDetailsDTO fetchAccount(String customerEmail);

    /**
     * Update account details
     * @param customerDetailsDTO CustomerDetailsDTO object
     * @return boolean sucess
     */
    boolean updateAccount(CustomerDetailsDTO customerDetailsDTO);

    /**
     * Delete account
     * @param customerEmail Customer email
     * @return boolean success
     */
    boolean deleteAccount(String customerEmail);
}
