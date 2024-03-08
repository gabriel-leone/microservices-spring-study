package com.gabrielleone.cards.models.mappers;

import com.gabrielleone.cards.models.DTOs.CardDTO;
import com.gabrielleone.cards.models.entities.Card;

public class CardMapper {
    public static CardDTO mapToCardsDTO(Card card, CardDTO cardDTO) {
        cardDTO.setEmail(card.getEmail());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setTotalLimit(card.getTotalLimit());
        cardDTO.setAmountUsed(card.getAmountUsed());
        cardDTO.setAmountAvailable(card.getAmountAvailable());
        return cardDTO;
    }

    public static Card mapToCard(CardDTO cardDTO, Card card) {
        card.setEmail(cardDTO.getEmail());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAmountUsed(cardDTO.getAmountUsed());
        card.setAmountAvailable(cardDTO.getAmountAvailable());
        return card;
    }
}
