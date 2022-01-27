package com.sgbu;

import java.math.BigDecimal;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void makeDeposit(Client client, BigDecimal amount) {
        Account clientAccount = this.accountRepository.findAccount(client);
        if(clientAccount == null) {
            throw new RuntimeException("Client not found");
        }
        clientAccount.deposit(amount);
    }

}
