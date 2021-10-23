package com.shrinil.addressbook.exception;

public class InternalError extends RuntimeException{

    public InternalError(String message) {
        super(message);
    }

    public InternalError() {
    }
}
