package com.shrinil.addressbook.model;

import lombok.Getter;

@Getter
public class UserDetails {

    private final String firstName;

    private final String lastName;

    private final String password;

    public UserDetails(String firstName, String lastName, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
