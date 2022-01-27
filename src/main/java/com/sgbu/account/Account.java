package com.sgbu.account;

import com.sgbu.OperationType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Account {
    private BigDecimal balance = ZERO;
    private final List<Operation> operationHistory = new ArrayList<>();

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        operationHistory.add(new Operation(OperationType.DEPOSIT, amount, Instant.now(), balance));
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

    public List<Operation> getHistory() {
        return operationHistory;
    }
}
