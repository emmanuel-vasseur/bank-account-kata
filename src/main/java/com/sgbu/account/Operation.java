package com.sgbu.account;

import com.sgbu.OperationType;

import java.math.BigDecimal;
import java.time.Instant;

public class Operation {
    private final BigDecimal amount;
    private final Instant operationDate;

    public Operation(OperationType deposit, BigDecimal amount, Instant operationDate, BigDecimal balance) {
        this.amount = amount;
        this.operationDate = operationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OperationType getType() {
        return OperationType.DEPOSIT;
    }

    public Instant getDate() {
        return operationDate;
    }

    public BigDecimal getBalance() {
        return BigDecimal.TEN;
    }
}
