package com.shrinil.addressbook.authenticator;

import com.shrinil.addressbook.exception.IncorrectAttributeException;
import com.shrinil.addressbook.model.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public class VerifyUserDetails extends Authenticator{
    public static void verify(UserDetails userDetails, MultipartFile file){
        String emailAddress = userDetails.getEmailAddress();
        if(emailAddress != null){
            if(!verifyEmailAddressFormat(emailAddress)){
                throw new IncorrectAttributeException("Invalid email address format.");
            }
        }

        if(!verifyMultipartFile(file)){
            throw new IncorrectAttributeException("Invalid multipart file");
        }
    }
}
