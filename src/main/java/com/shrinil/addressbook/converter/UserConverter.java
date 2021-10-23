package com.shrinil.addressbook.converter;

import com.shrinil.addressbook.manager.UserManager;
import com.shrinil.addressbook.model.User;
import com.shrinil.addressbook.model.UserDetails;

public class UserConverter {

    public static User userDetailsToUser(User user, UserDetails userDetails){
        if(userDetails.getFirstName() != null)
            user.setFirstName(userDetails.getFirstName());
        if(userDetails.getLastName() != null)
            user.setLastName(userDetails.getLastName());
        if(userDetails.getPassword() != null)
            user.setPassword(userDetails.getPassword());
        return user;
    }
}
