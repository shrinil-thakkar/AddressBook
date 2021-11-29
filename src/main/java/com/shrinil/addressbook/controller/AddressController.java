package com.shrinil.addressbook.controller;

import com.shrinil.addressbook.authenticator.VerifyAddressDetails;
import com.shrinil.addressbook.converter.AddressConverter;
import com.shrinil.addressbook.entity.Address;
import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.manager.AddressManager;
import com.shrinil.addressbook.manager.UserManager;
import com.shrinil.addressbook.model.AddressDetails;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user/{userId}/address")
public class AddressController {

    @Autowired
    private AddressManager addressManager;

    @Autowired
    private UserManager userManager;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> registerAddress(
            @NonNull @PathVariable Integer userId,
            @NonNull @RequestBody AddressDetails addressDetails){
        try {
            // To check if the user is valid
            User user = userManager.getUser(userId);
            VerifyAddressDetails.verify(addressDetails);
            Address address = addressManager.createAddress(user, addressDetails);
            addressDetails = AddressConverter.addressToAddressDetails(address);
        }
        catch (Exception e){
            Logger.getLogger("Exception while registering an address: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(addressDetails);
    }

    @PostMapping("/{addressId}")
    @ResponseBody
    public ResponseEntity<Object> updateAddress(
            @NonNull @PathVariable Integer userId,
            @NonNull @PathVariable Integer addressId,
            @NonNull @RequestBody AddressDetails addressDetails){
        try {
            // To check if the user is valid
            User user = userManager.getUser(userId);
            VerifyAddressDetails.verifyAddressBelongsToUser(user, addressId);
            VerifyAddressDetails.verify(addressDetails);

            Address address = addressManager.updateAddressDetails(addressId, addressDetails);
            addressDetails = AddressConverter.addressToAddressDetails(address);
        }
        catch (Exception e){
            Logger.getLogger("Exception while registering an address: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(addressDetails);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Object> getAddress(
            @NonNull @PathVariable Integer userId,
            @NonNull @PathVariable Integer addressId){
        AddressDetails addressDetails;
        try {
            // To check if the user is valid
            User user = userManager.getUser(userId);
            VerifyAddressDetails.verifyAddressBelongsToUser(user, addressId);

            Address address = addressManager.getAddress(addressId);
            addressDetails = AddressConverter.addressToAddressDetails(address);
        }
        catch (Exception e){
            Logger.getLogger("Exception while getting an address: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(addressDetails);
    }

    @GetMapping("")
    public ResponseEntity<List<AddressDetails>> getAllAddressForUser(
            @NonNull @PathVariable Integer userId){
        List<Address> addressList;
        try {
            // To check if the user is valid
            User user = userManager.getUser(userId);
            addressList = addressManager.getAllAddressForUser(user);
        }
        catch (Exception e){
            Logger.getLogger("Exception while getting all the addresses: " + e);
            return ResponseEntity.badRequest().build();
        }

        if(addressList == null || addressList.size() == 0)
            return ResponseEntity.noContent().build();

        List<AddressDetails> detailsList = new ArrayList<>();
        for(Address address:addressList)
            detailsList.add(AddressConverter.addressToAddressDetails(address));
        return ResponseEntity.ok(detailsList);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddressById(
            @NonNull @PathVariable Integer userId,
            @NonNull @PathVariable Integer addressId){
        try {
            // To check if the user is valid
            User user = userManager.getUser(userId);
            VerifyAddressDetails.verifyAddressBelongsToUser(user, addressId);

            addressManager.deleteAddressById(user, addressId);
        }
        catch (Exception e){
            Logger.getLogger("Exception while deleting an address: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Address deleted with the addressId: " + addressId);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllAddressForUser(
            @NonNull @PathVariable Integer userId){
        try {
            // To check if the user is valid
            User user = userManager.getUser(userId);
            addressManager.deleteAllAddressForUser(user);
        }
        catch (Exception e){
            Logger.getLogger("Exception while deleting all the addresses: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Deleted all the addresses for the userId: ." + userId);
    }
}
