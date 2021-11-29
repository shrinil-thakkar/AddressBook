package com.shrinil.addressbook.authenticator;

import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.exception.UserAddressMismatchException;
import com.shrinil.addressbook.model.AddressDetails;
import lombok.NonNull;

public class VerifyAddressDetails extends Authenticator{
    public static void verify(AddressDetails addressDetails){

    }

    public static void verifyAddressBelongsToUser(User user, @NonNull Integer addressId) {
        if(!user.getAddressList().contains(addressId))
            throw new UserAddressMismatchException(user.getUserId(), addressId);
    }
}
