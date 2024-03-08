package com.gabrielleone.cards.services.impl;

import com.gabrielleone.cards.exceptions.CardAlreadyExistsException;
import com.gabrielleone.cards.exceptions.ResourceNotFoundException;
import com.gabrielleone.cards.models.DTOs.CardDTO;
import com.gabrielleone.cards.models.entities.Card;
import com.gabrielleone.cards.models.mappers.CardMapper;
import com.gabrielleone.cards.repositories.ICardRepository;
import com.gabrielleone.cards.services.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private ICardRepository cardRepository;

    @Override
    public void createCard(CardDTO cardDTO) {
        Card card = CardMapper.mapToCard(cardDTO, new Card());
        cardRepository.findByCardNumber(cardDTO.getCardNumber()).ifPresent(c -> {
            throw new CardAlreadyExistsException("Card with card number " + c.getCardNumber() + "already exists.");
        });
        cardRepository.save(card);
    }

    @Override
    public CardDTO fetchCard(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "card number", cardNumber)
        );
        return CardMapper.mapToCardsDTO(card, new CardDTO());
    }

    @Override
    public boolean updateCard(CardDTO cardDTO) {
        boolean isUpdated = false;
        if (cardDTO != null) {
            Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Card", "card number", cardDTO.getCardNumber())
            );
            CardMapper.mapToCard(cardDTO, card);
            cardRepository.save(card);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCard(String cardNumber) {
        boolean isDeleted = false;
        if (cardNumber != null) {
            Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Card", "card number", cardNumber)
            );
            cardRepository.delete(card);
            isDeleted = true;
        }
        return isDeleted;
    }

}
