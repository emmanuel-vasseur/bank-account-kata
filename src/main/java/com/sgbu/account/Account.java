package com.sgbu.account;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Account {
    private BigDecimal balance = ZERO;
    private final List<Operation> operationHistory = new ArrayList<>();

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Operation> getHistory() {
        return List.copyOf(operationHistory);
    }

    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.add(amount);
        operationHistory.add(new Operation(OperationType.DEPOSIT, amount, Instant.now(), balance));
    }

    public void withdrawal(BigDecimal amount) {
        validatePositiveAmount(amount);
        validateSufficientBalance(amount);

        balance = balance.subtract(amount);
        operationHistory.add(new Operation(OperationType.WITHDRAWAL, amount, Instant.now(), balance));
    }

    private void validatePositiveAmount(BigDecimal amount) {
        if (ZERO.compareTo(amount) >= 0) {
            throw new ZeroOrNegativeAmountException();
        }
    }

    private void validateSufficientBalance(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientAmountException();
        }
    }
}
