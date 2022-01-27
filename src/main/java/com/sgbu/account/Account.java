package com.sgbu.account;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class Account {
    private BigDecimal balance = ZERO;

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdrawal(BigDecimal amount) {
        validatePositiveAmount(amount);

        if (amount.compareTo(balance) > 0) {
            throw new InsufficientAmountException();
        }

        this.balance = this.balance.subtract(amount);
    }

    private void validatePositiveAmount(BigDecimal amount) {
        if (ZERO.compareTo(amount) >= 0) {
            throw new ZeroOrNegativeAmountException();
        }
    }
}
