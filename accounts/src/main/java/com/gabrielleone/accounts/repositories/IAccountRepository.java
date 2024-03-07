package com.gabrielleone.accounts.repositories;

import com.gabrielleone.accounts.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);
}
