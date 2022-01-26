package com.sgbu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MakeADepositTest {

    @Test
    void make_a_deposit_add_amount_to_balance() {
        Account account = new Account();
        assertThat(account.getBalance()).isEqualTo(0);
        account.deposit(200);
        assertThat(account.getBalance()).isEqualTo(200);
    }
}
