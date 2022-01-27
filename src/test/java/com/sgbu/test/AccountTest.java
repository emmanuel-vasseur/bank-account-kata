package com.sgbu.test;

import com.sgbu.account.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.Instant;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
class AccountTest {

    @Test
    void create_an_account_should_initialize_balance_to_zero() {
        Account account = new Account();
        assertThat(account.getBalance()).isEqualTo(ZERO);
    }

    @ParameterizedTest
    @ValueSource(strings = {"201.01", "530.10"})
    void make_a_deposit_should_add_amount_to_balance(String amount) {
        Account account = new Account();
        account.deposit(new BigDecimal(amount));
        assertThat(account.getBalance()).isEqualTo(amount);
    }

    @Test
    void make_multiple_deposits_should_add_all_amounts_to_balance() {
        Account account = new Account();
        account.deposit(new BigDecimal("14"));
        account.deposit(new BigDecimal("530.10"));
        account.deposit(new BigDecimal("201.01"));
        assertThat(account.getBalance()).isEqualTo("745.11");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void make_a_deposit_with_zero_or_negative_amount_should_fail(String negativeOrZero) {
        Account account = new Account();

        assertThatThrownBy(() -> account.deposit(new BigDecimal(negativeOrZero)))
                .isInstanceOf(ZeroOrNegativeAmountException.class)
                .hasMessage("Zero or negative amount");

        assertThatAccountHasNotBeenModified(account);
    }

    @Test
    void make_a_withdrawal_with_insufficient_balance_should_fail() {
        Account account = new Account();

        assertThatThrownBy(() -> account.withdrawal(BigDecimal.ONE))
                .isInstanceOf(InsufficientAmountException.class)
                .hasMessage("Insufficient amount");

        assertThatAccountHasNotBeenModified(account);
    }

    @Test
    void make_a_withdrawal_with_sufficient_balance_should_subtract_amount_to_balance() {
        Account account = new Account();
        account.deposit(BigDecimal.TEN);
        account.withdrawal(BigDecimal.ONE);
        assertThat(account.getBalance()).isEqualTo("9");
    }

    @Test
    void make_a_withdrawal_of_all_balance_should_set_balance_to_zero() {
        Account account = new Account();
        account.deposit(BigDecimal.ONE);
        account.withdrawal(BigDecimal.ONE);
        assertThat(account.getBalance()).isEqualTo(ZERO);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void make_a_withdrawal_with_zero_or_negative_amount_should_fail(String negativeOrZero) {
        Account account = new Account();

        assertThatThrownBy(() -> account.withdrawal(new BigDecimal(negativeOrZero)))
                .isInstanceOf(ZeroOrNegativeAmountException.class)
                .hasMessage("Zero or negative amount");

        assertThatAccountHasNotBeenModified(account);
    }

    @Test
    void operation_history_of_a_new_account_should_be_empty() {
        assertThat(new Account().getHistory()).isEmpty();
    }

    @Test
    void operation_history_of_an_account_with_a_deposit_should_contains_one_operation_with_correct_attributes() {
        Account account = new Account();
        Instant before = Instant.now();
        account.deposit(BigDecimal.ONE);
        Instant after = Instant.now();

        assertThat(account.getHistory()).hasSize(1);
        Operation depositOperation = account.getHistory().get(0);
        assertThat(depositOperation.getAmount()).isEqualTo(BigDecimal.ONE);
        assertThat(depositOperation.getOperationType()).isEqualTo(OperationType.DEPOSIT);
        assertThat(depositOperation.getBalance()).isEqualTo(BigDecimal.ONE);
        assertThat(depositOperation.getDate()).isBetween(before, after);
    }

    @Test
    void operation_history_of_an_account_with_a_deposit_then_a_withdrawal_should_contains_two_operations_with_withdrawal_correct_attributes() {
        // Setup
        Account account = new Account();
        account.deposit(BigDecimal.TEN);

        // Test
        Instant before = Instant.now();
        account.withdrawal(BigDecimal.ONE);
        Instant after = Instant.now();

        // Assert
        assertThat(account.getHistory()).hasSize(2);
        Operation withdrawalOperation = account.getHistory().get(1);
        assertThat(withdrawalOperation.getAmount()).isEqualTo(BigDecimal.ONE);
        assertThat(withdrawalOperation.getOperationType()).isEqualTo(OperationType.WITHDRAWAL);
        assertThat(withdrawalOperation.getBalance()).isEqualTo("9");
        assertThat(withdrawalOperation.getDate()).isBetween(before, after);
    }

    private void assertThatAccountHasNotBeenModified(Account account) {
        assertThat(account.getBalance()).isEqualTo(ZERO);
        assertThat(account.getHistory()).isEmpty();
    }

}
