package com.sgbu;

import com.sgbu.account.Account;
import com.sgbu.account.Operation;
import com.sgbu.client.Client;
import com.sgbu.client.ClientNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void makeDeposit(Client client, BigDecimal amount) {
        Account clientAccount = getClientAccount(client);
        clientAccount.deposit(amount);
    }

    public void makeWithdrawal(Client client, BigDecimal amount) {
        Account clientAccount = getClientAccount(client);
        clientAccount.withdrawal(amount);
    }

    public List<Operation> getHistory(Client client) {
        return getClientAccount(client).getHistory();
    }

    private Account getClientAccount(Client client) {
        return this.accountRepository.findAccount(client)
                .orElseThrow(() -> new ClientNotFoundException(client));
    }
}
