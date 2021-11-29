package com.shrinil.addressbook.authenticator;

import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticator {
    public void verifyNumeric(String data){
    }

    public static boolean verifyPhoneNumberFormat(String str){
        Pattern pattern = Pattern.compile("[7-9][0-9]{9}");
        Matcher match = pattern.matcher(str);
        return (match.find() && match.group().equals(str));
    }

    public static boolean verifyEmailAddressFormat(String str){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        return (match.find() && match.group().equals(str));
    }

    public static boolean verifyMultipartFile(MultipartFile file){
        if(file == null)
            return true;
        return !file.isEmpty();
    }
}
