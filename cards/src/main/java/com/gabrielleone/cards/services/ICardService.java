package com.gabrielleone.cards.services;

import com.gabrielleone.cards.models.DTOs.CardDTO;

public interface ICardService {

    /**
     * Create a new card
     * @param email String email
     */
    void createCard(String email);

    CardDTO fetchCard(String cardNumber);

    boolean updateCard(CardDTO cardDTO);

    boolean deleteCard(String cardNumber);
}
