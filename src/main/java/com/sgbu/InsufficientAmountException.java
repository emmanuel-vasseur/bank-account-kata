package com.sgbu;

public class InsufficientAmountException extends RuntimeException {
    public InsufficientAmountException() {
        super("Insufficient amount");
    }
}
