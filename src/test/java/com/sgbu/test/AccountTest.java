package com.sgbu.test;

import com.sgbu.Account;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
class AccountTest {

    @Test
    void create_an_account_should_initialize_balance_to_zero() {
        Account account = new Account();
        assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
    }

    @ParameterizedTest
    @ValueSource(strings = {"201.01", "530.10"})
    void make_a_deposit_should_add_amount_to_balance(String amount) {
        Account account = new Account();
        account.deposit(new BigDecimal(amount));
        assertThat(account.getBalance()).isEqualTo(new BigDecimal(amount));
    }

    @Test
    void make_multiple_deposits_should_add_all_amounts_to_balance() {
        Account account = new Account();
        account.deposit(new BigDecimal("14"));
        account.deposit(new BigDecimal("530.10"));
        account.deposit(new BigDecimal("201.01"));
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("745.11"));
    }

    @Test
    void make_a_withdrawal_with_insufficient_balance_should_fail() {
        Account account = new Account();
        assertThatThrownBy(() -> account.withdrawal(BigDecimal.ONE))
                .hasMessage("Insufficient amount");
    }

    @Test
    void make_a_withdrawal_with_sufficient_balance_should_subtract_amount_to_balance() {
        Account account = new Account();
        account.deposit(BigDecimal.TEN);
        account.withdrawal(BigDecimal.ONE);
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("9"));
    }

    @Test
    void make_a_withdrawal_of_all_balance_should_set_balance_to_zero() {
        Account account = new Account();
        account.deposit(BigDecimal.ONE);
        account.withdrawal(BigDecimal.ONE);
        assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
    }
}
