package com.shrinil.addressbook.model;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class UserDetails {

    @NotBlank(message = "First Name cannot be null")
    @Size(max = 30, message = "Maximum 30 characters allowed for First Name")
    private final String firstName;

    @NotBlank(message = "First Name cannot be null")
    @Size(max = 30, message = "Maximum 30 characters allowed for Last Name")
    private final String lastName;

    @Email(message = "Invalid email address format")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email address format")
    private final String emailAddress;

    public UserDetails(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
}
