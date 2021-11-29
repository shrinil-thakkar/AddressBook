package com.shrinil.addressbook.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer addressId) {
        super("Account with the addressId=" + addressId + " not found.");
    }
}
