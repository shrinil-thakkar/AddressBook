package com.shrinil.addressbook.controller;

import com.shrinil.addressbook.authenticator.VerifyPhoneDetails;
import com.shrinil.addressbook.authenticator.VerifyUserDetails;
import com.shrinil.addressbook.builder.UserBuilder;
import com.shrinil.addressbook.manager.UserManager;
import com.shrinil.addressbook.model.PhoneDetails;
import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.model.UserDetails;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping("/registerPhone")
    @ResponseBody
    public ResponseEntity<Object> registerPhone(
            @NonNull @RequestBody PhoneDetails phoneDetails){
        User user;
        String phoneNumber = phoneDetails.getPhoneNumber();
        try {
            VerifyPhoneDetails.verify(phoneDetails);
            user = new UserBuilder().setPhoneNumber(phoneNumber).build();
            user = userManager.createUser(user);
        }
        catch (Exception e){
            Logger.getLogger("Exception while registering a user: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userId}/registerDetails")
    @ResponseBody
    public ResponseEntity<Object> registerUserDetails(
            @NonNull @PathVariable Integer userId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("emailAddress") String emailAddress,
            @RequestParam("displayPicture") MultipartFile multipartFile){
        User user;
        try {
            UserDetails userDetails = new UserDetails(firstName, lastName, emailAddress);
            VerifyUserDetails.verify(userDetails, multipartFile);
            user = userManager.updateUserDetails(userId, userDetails, multipartFile);
        }
        catch (Exception e){
            Logger.getLogger("Exception while registering a user: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(
            @NonNull @PathVariable Integer userId){
        User user;
        try {
            user = userManager.getUser(userId);
        }
        catch (Exception e){
            Logger.getLogger("Exception while getting a user: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList;
        try {
            userList = userManager.getAllUsers();
        }
        catch (Exception e){
            Logger.getLogger("Exception while getting all the users: " + e);
            return ResponseEntity.badRequest().build();
        }

        if(userList == null || userList.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @NonNull @PathVariable Integer userId){
        try {
            userManager.deleteUser(userId);
        }
        catch (Exception e){
            Logger.getLogger("Exception while deleting a user: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User deleted with userId: " + userId);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllUsers(){
        try {
            userManager.deleteAllUsers();
        }
        catch (Exception e){
            Logger.getLogger("Exception while deleting all the users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Deleted all the users.");
    }

    @GetMapping("/{userId}/displayPicture")
    public ResponseEntity<Object> getUserDisplayPicture(
            @NonNull @PathVariable Integer userId){
        Byte[] userDp;
        try {
            userDp = userManager.getUserDisplayPicture(userId);
        }
        catch (Exception e){
            Logger.getLogger("Exception while getting a user: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userDp);
    }

}
