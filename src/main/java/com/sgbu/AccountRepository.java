package com.sgbu;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findAccount(Client client);
}
