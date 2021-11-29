package com.shrinil.addressbook.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Integer userId){
        super("Account with the userId=" + userId + " not found.");
    }
}
