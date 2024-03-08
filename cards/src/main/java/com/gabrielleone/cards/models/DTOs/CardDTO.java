package com.gabrielleone.cards.models.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CardDTO {
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Card number should not be empty")
    @Pattern(regexp = "^[0-9]{12}$", message = "Card number should be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card type should not be empty")
    private String cardType;

    @NotNull(message = "Total limit should not be empty")
    @Positive(message = "Total limit should be at least 1")
    private float totalLimit;

    @NotNull(message = "Amount used should not be empty")
    @PositiveOrZero(message = "Amount used should be at least 0")
    private float amountUsed;

    @NotNull(message = "Amount available should not be empty")
    @PositiveOrZero(message = "Amount available should be at least 0")
    private float amountAvailable;
}
