package com.sgbu;

import com.sgbu.account.Account;
import com.sgbu.client.Client;
import com.sgbu.client.ClientNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void makeDeposit(Client client, BigDecimal amount) {
        Optional<Account> clientAccount = this.accountRepository.findAccount(client);
        if (clientAccount.isEmpty()) {
            throw new ClientNotFoundException(client);
        }
        clientAccount.get().deposit(amount);
    }

    public void makeWithdrawal(Client client, BigDecimal amount) {
        throw new ClientNotFoundException(client);
    }
}
