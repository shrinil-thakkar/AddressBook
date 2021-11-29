package com.shrinil.addressbook.exception;

public class UserDisplayPictureNotFoundException extends RuntimeException {
    public UserDisplayPictureNotFoundException(String message) {
        super(message);
    }

    public UserDisplayPictureNotFoundException(Integer userId){
        super("Display picture for the user with the userId=" + userId + " not found.");
    }
}
