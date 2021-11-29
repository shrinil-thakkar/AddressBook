package com.shrinil.addressbook.exception;

public class UserAddressMismatchException extends RuntimeException {
    public UserAddressMismatchException(String message) {
        super(message);
    }
    public UserAddressMismatchException(Integer userId, Integer addressId) {
        super("The address with addressId: " + addressId
                + " does not belong to the user with userId: " + userId);
    }
}
