package com.sgbu.account;

public class InsufficientAmountException extends RuntimeException {
    public InsufficientAmountException() {
        super("Insufficient amount");
    }
}
