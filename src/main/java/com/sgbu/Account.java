package com.sgbu;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class Account {
    private BigDecimal balance = ZERO;

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        if(ZERO.compareTo(amount) >= 0) {
            throw new RuntimeException("Invalid operation amount");
        }

        this.balance = this.balance.add(amount);
    }

    public void withdrawal(BigDecimal amount) {
        if(ZERO.compareTo(amount) >= 0) {
            throw new RuntimeException("Invalid operation amount");
        }

        if(amount.compareTo(balance) > 0) {
            throw new RuntimeException("Insufficient amount");
        }

        this.balance = this.balance.subtract(amount);
    }
}
