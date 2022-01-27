package com.sgbu;

import com.sgbu.account.Account;
import com.sgbu.client.Client;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findAccount(Client client);
}
