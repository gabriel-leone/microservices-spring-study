package com.gabrielleone.cards.repositories;

import com.gabrielleone.cards.models.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);

    Optional<Card> findByEmail(String email);
}
