package com.gabrielleone.cards.services.impl;

import com.gabrielleone.cards.constants.CardConstants;
import com.gabrielleone.cards.exceptions.CardAlreadyExistsException;
import com.gabrielleone.cards.exceptions.ResourceNotFoundException;
import com.gabrielleone.cards.models.DTOs.CardDTO;
import com.gabrielleone.cards.models.entities.Card;
import com.gabrielleone.cards.models.mappers.CardMapper;
import com.gabrielleone.cards.repositories.ICardRepository;
import com.gabrielleone.cards.services.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private ICardRepository cardRepository;

    @Override
    public void createCard(String email) {
        cardRepository.findByEmail(email).ifPresent(c -> {
            throw new CardAlreadyExistsException("Card with email " + email + " already exists.");
        });
        cardRepository.save(createNewCard(email));
    }

    private Card createNewCard(String email) {
        Card newCard = new Card();
        newCard.setEmail(email);
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAmountAvailable(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDTO fetchCard(String email) {
        Card card = cardRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Card", "email", email)
        );
        return CardMapper.mapToCardsDTO(card, new CardDTO());
    }

    @Override
    public boolean updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "card number", cardDTO.getCardNumber())
        );
        CardMapper.mapToCard(cardDTO, card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String email) {
        Card card = cardRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Card", "email", email)
        );
        cardRepository.delete(card);
        return true;
    }

}
