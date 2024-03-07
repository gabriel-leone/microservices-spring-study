package com.gabrielleone.accounts.controller;

import com.gabrielleone.accounts.constants.AccountConstants;
import com.gabrielleone.accounts.models.DTOs.CustomerDTO;
import com.gabrielleone.accounts.models.DTOs.CustomerDetailsDTO;
import com.gabrielleone.accounts.models.DTOs.ResponseDTO;
import com.gabrielleone.accounts.models.entities.Customer;
import com.gabrielleone.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {

        accountService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDTO> getAccountDetails(@RequestParam String email) {
        var customerDetails = accountService.fetchAccount(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody CustomerDetailsDTO customerDetailsDTO) {
        boolean isUpdated = accountService.updateAccount(customerDetailsDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam String email) {
        boolean isDeleted = accountService.deleteAccount(email);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

}
