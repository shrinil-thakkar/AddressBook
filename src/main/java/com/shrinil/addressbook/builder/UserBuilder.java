package com.shrinil.addressbook.builder;

import com.shrinil.addressbook.model.User;

public class UserBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(firstName, lastName, phoneNumber, password);
    }
}