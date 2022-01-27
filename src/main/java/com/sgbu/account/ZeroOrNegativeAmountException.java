package com.sgbu.account;

public class ZeroOrNegativeAmountException extends RuntimeException {
    public ZeroOrNegativeAmountException() {
        super("Zero or negative amount");
    }
}
