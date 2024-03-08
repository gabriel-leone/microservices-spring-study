package com.gabrielleone.cards.services;

import com.gabrielleone.cards.models.DTOs.CardDTO;

public interface ICardService {

    /**
     * Create a new card
     * @param cardDTO CardDTO object
     */
    void createCard(CardDTO cardDTO);


    CardDTO fetchCard(String cardNumber);

    boolean updateCard(CardDTO cardDTO);

    boolean deleteCard(String cardNumber);
}
