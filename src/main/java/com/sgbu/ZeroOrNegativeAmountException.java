package com.sgbu;

public class ZeroOrNegativeAmountException extends RuntimeException {
    public ZeroOrNegativeAmountException() {
        super("Zero or negative amount");
    }
}
