package com.shrinil.addressbook.builder;

import com.shrinil.addressbook.entity.User;

public class UserBuilder {
    private String phoneNumber;

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User build() {
        return new User(null, null, phoneNumber);
    }
}