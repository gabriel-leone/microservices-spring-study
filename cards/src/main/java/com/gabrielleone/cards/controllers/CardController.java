package com.gabrielleone.cards.controllers;

import com.gabrielleone.cards.constants.CardConstants;
import com.gabrielleone.cards.models.DTOs.CardDTO;
import com.gabrielleone.cards.models.DTOs.ResponseDTO;
import com.gabrielleone.cards.services.ICardService;
import jakarta.validation.Valid;
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
public class CardController {
    private ICardService cardService;

    @PostMapping("/createCard")
    public ResponseEntity<ResponseDTO> createCard(@Valid @RequestBody CardDTO cardDTO) {
        cardService.createCard(cardDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDTO> getCard(@RequestParam String cardNumber) {
        var card = cardService.fetchCard(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @PutMapping("/updateCard")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardDTO cardDTO) {
        boolean isUpdated = cardService.updateCard(cardDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam String cardNumber) {
        boolean isDeleted = cardService.deleteCard(cardNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }
}
