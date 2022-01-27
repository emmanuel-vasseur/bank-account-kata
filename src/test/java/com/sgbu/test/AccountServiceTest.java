package com.sgbu.test;

import com.sgbu.AccountService;
import com.sgbu.Client;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
class AccountServiceTest {

    @Test
    void make_a_deposit_for_an_non_existing_client_should_fail(){
        AccountService accountService = new AccountService();
        assertThatThrownBy(() -> accountService.makeDeposit(new Client("inexistant"), BigDecimal.TEN))
                .hasMessage("Client not found");
    }

}
