package com.sgbu.test;

import com.sgbu.Account;
import com.sgbu.AccountRepository;
import com.sgbu.AccountService;
import com.sgbu.Client;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
class AccountServiceTest {

    @Test
    void make_a_deposit_for_an_non_existing_client_should_fail(){
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        assertThatThrownBy(() -> accountService.makeDeposit(new Client("inexistant"), BigDecimal.TEN))
                .hasMessage("Client not found");
    }

    @Test
    void make_a_deposit_for_an_existing_client_should_make_deposit_on_client_account(){
        Client client = new Client("client");
        Account clientAccount = Mockito.mock(Account.class);
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        Mockito.when(accountRepository.findAccount(client)).thenReturn(clientAccount);

        AccountService accountService = new AccountService(accountRepository);
        accountService.makeDeposit(client, BigDecimal.TEN);

        Mockito.verify(clientAccount).deposit(BigDecimal.TEN);
        Mockito.verifyNoMoreInteractions(clientAccount);
    }
}
