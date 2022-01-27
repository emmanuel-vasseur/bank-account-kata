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

    public void withdrawal(BigDecimal amount) {
        if(amount.compareTo(balance) > 0) {
            throw new RuntimeException("Insufficient amount");
        }
        this.balance = this.balance.subtract(amount);
    }
}
