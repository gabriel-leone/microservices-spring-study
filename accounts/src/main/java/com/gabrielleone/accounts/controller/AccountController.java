package com.gabrielleone.accounts.controller;

import com.gabrielleone.accounts.constants.AccountConstants;
import com.gabrielleone.accounts.models.DTOs.*;
import com.gabrielleone.accounts.services.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in EazyBank",
        description = "This controller exposes CRUD operations for the Accounts microservice in EazyBank"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountContactInfoDTO accountContactInfoDTO;

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-info")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountContactInfoDTO);
    }

    @Operation(
            summary = "Fetch account details REST API",
            description = "This endpoint fetches the account details for a customer in EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDTO> getAccountDetails(@RequestParam @Email String email) {
        var customerDetails = accountService.fetchAccount(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
    }

    @Operation(
            summary = "Create a new account REST API",
            description = "This endpoint creates a new account for a customer in EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Account creation failed. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {

        accountService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Update account details REST API",
            description = "This endpoint updates the account details for a customer in EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Account update failed. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
            )
            )
    }
    )
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDetailsDTO customerDetailsDTO) {
        boolean isUpdated = accountService.updateAccount(customerDetailsDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete account REST API",
            description = "This endpoint deletes the account for a customer in EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Account deletion failed. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
            )
    }
    )
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam @Email String email) {
        boolean isDeleted = accountService.deleteAccount(email);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }

}
