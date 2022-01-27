package com.sgbu.test;

import com.sgbu.AccountRepository;
import com.sgbu.AccountService;
import com.sgbu.account.Account;
import com.sgbu.account.Operation;
import com.sgbu.client.Client;
import com.sgbu.client.ClientNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    void make_a_deposit_for_an_non_existing_client_should_fail() {
        Mockito.when(accountRepository.findAccount(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.makeDeposit(new Client("non-existent-client"), BigDecimal.TEN))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client not found: non-existent-client");
    }

    @ParameterizedTest
    @ValueSource(strings = {"201.01", "530.10"})
    void make_a_deposit_for_an_existing_client_should_make_deposit_on_client_account(String amount) {
        // Setup
        Client client = new Client("client");
        Account clientAccount = Mockito.mock(Account.class);
        Mockito.when(accountRepository.findAccount(client)).thenReturn(Optional.of(clientAccount));

        // Test
        accountService.makeDeposit(client, new BigDecimal(amount));

        // Assert
        Mockito.verify(clientAccount).deposit(new BigDecimal(amount));
        Mockito.verifyNoMoreInteractions(clientAccount);
    }

    @Test
    void make_a_withdrawal_for_an_non_existing_client_should_fail() {
        assertThatThrownBy(() -> accountService.makeWithdrawal(new Client("non-existent-client-2"), BigDecimal.TEN))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client not found: non-existent-client-2");
    }

    @ParameterizedTest
    @ValueSource(strings = {"201.01", "530.10"})
    void make_a_withdrawal_for_an_existing_client_should_make_withdrawal_on_client_account(String amount) {
        // Setup
        Client client = new Client("client");
        Account clientAccount = Mockito.mock(Account.class);
        Mockito.when(accountRepository.findAccount(client)).thenReturn(Optional.of(clientAccount));

        // Test
        accountService.makeWithdrawal(client, new BigDecimal(amount));

        // Assert
        Mockito.verify(clientAccount).withdrawal(new BigDecimal(amount));
        Mockito.verifyNoMoreInteractions(clientAccount);
    }

    @Test
    void get_history_for_an_non_existing_client_should_fail() {
        assertThatThrownBy(() -> accountService.getAccountHistory(new Client("non-existent-client-3")))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client not found: non-existent-client-3");
    }

    @Test
    void get_history_for_an_existing_client_should_return_operation_history_of_client_account() {
        // Setup
        Client client = new Client("client");
        Account clientAccount = new Account();
        clientAccount.deposit(BigDecimal.TEN);
        Mockito.when(accountRepository.findAccount(client)).thenReturn(Optional.of(clientAccount));

        // Test
        List<Operation> accountOperations = accountService.getAccountHistory(client);

        // Assert
        assertThat(accountOperations)
                .hasSize(1)
                .isEqualTo(clientAccount.getHistory());
    }
}
