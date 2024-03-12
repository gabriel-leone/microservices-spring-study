package com.gabrielleone.loans.controllers;

import com.gabrielleone.loans.constants.LoanConstants;
import com.gabrielleone.loans.models.DTOs.LoanDTO;
import com.gabrielleone.loans.models.DTOs.ResponseDTO;
import com.gabrielleone.loans.services.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {

    private ILoanService iLoanService;

    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @Email String email) {
        iLoanService.createLoan(email);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam @Email String email) {
        LoanDTO loansDto = iLoanService.fetchLoan(email);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoanDTO loansDto) {
        boolean isUpdated = iLoanService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/deleteLoan")
    public ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam @Email String email) {
        boolean isDeleted = iLoanService.deleteLoan(email);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }

}
