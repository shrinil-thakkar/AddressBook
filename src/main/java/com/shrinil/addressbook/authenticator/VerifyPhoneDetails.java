package com.shrinil.addressbook.authenticator;

import com.shrinil.addressbook.exception.IncorrectAttributeException;
import com.shrinil.addressbook.model.PhoneDetails;

public class VerifyPhoneDetails extends Authenticator{

    public static void verify(PhoneDetails phoneDetails){
        String phoneNumber = phoneDetails.getPhoneNumber();
        if(phoneNumber != null) {
            if (!verifyPhoneNumberFormat(phoneNumber)) {
                throw new IncorrectAttributeException("Invalid phone number format.");
            }
        }
    }
}
