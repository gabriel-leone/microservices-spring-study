package com.gabrielleone.accounts.exceptions;

import com.gabrielleone.accounts.models.DTOs.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getContextPath(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getContextPath(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception,
                                                           HttpServletRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getContextPath(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDTO);
    }
}
