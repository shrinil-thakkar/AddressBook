package com.shrinil.addressbook.converter;

import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.entity.UserDisplayPicture;
import com.shrinil.addressbook.model.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UserConverter {

    public static void userDetailsToUser(
            User user, UserDetails userDetails, UserDisplayPicture userDp, MultipartFile multipartFile){
        if(userDetails.getFirstName() != null)
            user.setFirstName(userDetails.getFirstName());
        if(userDetails.getLastName() != null)
            user.setLastName(userDetails.getLastName());
        if(userDetails.getEmailAddress() != null)
            user.setEmailAddress(userDetails.getEmailAddress());

        userDp.setUser(user);
        if(multipartFile != null && !multipartFile.isEmpty()){
            try {
                byte[] bytes = multipartFile.getBytes();
                userDp.setDisplayPicture(byteArrayToByteArray(bytes));
            }
            catch (IOException ioException){
                // Add a log here to indicate exception occurred
            }
        }
    }

    public static Byte[] byteArrayToByteArray(byte[] bytes){
        if(bytes == null || bytes.length == 0)
            return null;
        Byte[] Bytes = new Byte[bytes.length];
        int i = 0;
        for (byte b: bytes)
            Bytes[i++] = b;
        return Bytes;
    }

    public static byte[] ByteArrayTobyteArray(Byte[] Bytes){
        if(Bytes == null || Bytes.length == 0)
            return null;
        byte[] bytes = new byte[Bytes.length];
        int i = 0;
        for (Byte b: Bytes){
            bytes[i++] = b;
        }
        return bytes;
    }
}
