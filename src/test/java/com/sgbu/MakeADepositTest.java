package com.sgbu;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class MakeADepositTest {

    @Test
    void create_an_account_should_initialize_balance_to_zero() {
        Account account = new Account();
        assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void make_a_deposit_should_add_amount_to_balance() {
        Account account = new Account();
        account.deposit(new BigDecimal("201.01"));
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("201.01"));
    }

    @Test
    void make_a_deposit_for_an_non_existing_client_should_fail(){
        AccountService accountService = new AccountService();
        assertThatThrownBy(() -> accountService.makeDeposit(BigDecimal.TEN))
                .hasMessage("Client not found");
    }
}
