package com.sgbu;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
