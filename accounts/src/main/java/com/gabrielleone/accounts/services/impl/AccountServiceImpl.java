package com.gabrielleone.accounts.services.impl;

import com.gabrielleone.accounts.constants.AccountConstants;
import com.gabrielleone.accounts.exceptions.CustomerAlreadyExistsException;
import com.gabrielleone.accounts.exceptions.ResourceNotFoundException;
import com.gabrielleone.accounts.models.DTOs.CustomerDTO;
import com.gabrielleone.accounts.models.DTOs.CustomerDetailsDTO;
import com.gabrielleone.accounts.models.entities.Account;
import com.gabrielleone.accounts.models.entities.Customer;
import com.gabrielleone.accounts.models.mapper.CustomerDetailsMapper;
import com.gabrielleone.accounts.models.mapper.CustomerMapper;
import com.gabrielleone.accounts.repositories.IAccountRepository;
import com.gabrielleone.accounts.repositories.ICustomerRepository;
import com.gabrielleone.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository;

    private ICustomerRepository customerRepository;

        @Override
        public void createAccount(CustomerDTO customerDTO) {
            Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
            customerRepository.findByEmail(customerDTO.getEmail()).ifPresent(c -> {
                throw new CustomerAlreadyExistsException("Customer with email " + c.getEmail() + " already exists.");
            });
            customer.setCreatedAt(LocalDateTime.now());
            customer.setCreatedBy("Anonymous");
            Customer savedCustomer = customerRepository.save(customer);
            accountRepository.save(createNewAccount(savedCustomer));
        }

        private Account createNewAccount(Customer customer) {
            Account newAccount = new Account();
            newAccount.setCustomerId(customer.getCustomerId());
            long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

            newAccount.setAccountNumber(randomAccountNumber);
            newAccount.setAccountType(AccountConstants.SAVINGS);
            newAccount.setBranchAddress(AccountConstants.ADDRESS);
            newAccount.setCreatedAt(LocalDateTime.now());
            newAccount.setCreatedBy("Anonymous");
            return newAccount;
        }

        @Override
        public CustomerDetailsDTO fetchAccount(String customerEmail) {
            Customer customer = customerRepository.findByEmail(customerEmail).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "email", customerEmail)
            );

            Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId().toString())
            );
            return CustomerDetailsMapper
                    .mapToCustomerDetailsDTO(customer, account, new CustomerDetailsDTO());
        }

        @Override
        public boolean updateAccount(CustomerDetailsDTO customerDetailsDTO) {
            boolean isUpdated = false;
            if (customerDetailsDTO != null) {
                Account account = accountRepository.findById(customerDetailsDTO.getAccountNumber()).orElseThrow(
                        () -> new ResourceNotFoundException("Account", "account number", customerDetailsDTO.getAccountNumber().toString())
                );

                CustomerDetailsMapper.mapToAccount(customerDetailsDTO, account);
                accountRepository.save(account);

                Long customerId = account.getCustomerId();
                Customer customer = customerRepository.findById(customerId).orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "customer id", customerId.toString())
                );

                CustomerDetailsMapper.mapToCustomer(customerDetailsDTO, customer);
                customerRepository.save(customer);

                account.setAccountType(customerDetailsDTO.getAccountType());
                account.setBranchAddress(customerDetailsDTO.getBranchAddress());
                accountRepository.save(account);
                isUpdated = true;
            }
            return isUpdated;
        }

        @Override
        public boolean deleteAccount(String customerEmail) {
            Customer customer = customerRepository.findByEmail(customerEmail).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "email", customerEmail)
            );

            Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId().toString())
            );

            accountRepository.delete(account);
            customerRepository.delete(customer);
            return true;
        }
}
