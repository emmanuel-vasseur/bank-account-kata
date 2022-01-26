package com.sgbu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MakeADepositTest {

    @Test
    void create_an_account_should_initialize_balance_to_zero() {
        Account account = new Account();
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    void make_a_deposit_should_add_amount_to_balance() {
        Account account = new Account();
        account.deposit(200);
        assertThat(account.getBalance()).isEqualTo(200);
    }
}
