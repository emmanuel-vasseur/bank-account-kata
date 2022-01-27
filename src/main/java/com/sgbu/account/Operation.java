package com.sgbu.account;

import java.math.BigDecimal;

public class Operation {
    private final BigDecimal amount;

    public Operation(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
