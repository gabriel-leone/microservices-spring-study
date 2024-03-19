package com.gabrielleone.loans.controllers;

import com.gabrielleone.loans.constants.LoanConstants;
import com.gabrielleone.loans.models.DTOs.LoanContactInfoDTO;
import com.gabrielleone.loans.models.DTOs.LoanDTO;
import com.gabrielleone.loans.models.DTOs.ResponseDTO;
import com.gabrielleone.loans.models.entities.Loan;
import com.gabrielleone.loans.services.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoanController {

    private final ILoanService loanService;

    public LoanController(ILoanService iLoanService) {
        this.loanService = iLoanService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private LoanContactInfoDTO cardContactInfoDTO;

    @Autowired
    private Environment environment;

    @GetMapping("/contact-info")
    public ResponseEntity<LoanContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardContactInfoDTO);
    }

    @GetMapping("/java-info")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @Email String email) {
        loanService.createLoan(email);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam @Email String email) {
        LoanDTO loansDto = loanService.fetchLoan(email);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoanDTO loansDto) {
        boolean isUpdated = loanService.updateLoan(loansDto);
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
        boolean isDeleted = loanService.deleteLoan(email);
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
