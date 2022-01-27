package com.sgbu.test;

import com.sgbu.*;
import com.sgbu.account.Account;
import com.sgbu.client.Client;
import com.sgbu.client.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @BeforeEach
    void accountRepositoryDefaultBehavior() {
        Mockito.when(accountRepository.findAccount(any())).thenReturn(Optional.empty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"non-existent-client-1", "non-existent-client-2"})
    void make_a_deposit_for_an_non_existing_client_should_fail(String clientId) {
        assertThatThrownBy(() -> accountService.makeDeposit(new Client(clientId), BigDecimal.TEN))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client not found: " + clientId);
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

    @ParameterizedTest
    @ValueSource(strings = {"non-existent-client-1", "non-existent-client-2"})
    void make_a_withdrawal_for_an_non_existing_client_should_fail(String clientId) {
        assertThatThrownBy(() -> accountService.makeWithdrawal(new Client(clientId), BigDecimal.TEN))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client not found: " + clientId);
    }
}
