package com.shrinil.addressbook.model;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class PhoneDetails {

    @Pattern(regexp = "[7-9][0-9]{9}", message = "Invalid phone number format")
    String phoneNumber;
}
