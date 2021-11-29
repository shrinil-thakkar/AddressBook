package com.shrinil.addressbook.controller;

import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.manager.UserManager;
import com.shrinil.addressbook.model.UserAccessDetails;
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

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("userLink")
public class UserAccessController {

    @Autowired
    private UserManager userManager;

    @PostMapping("/check")
    @ResponseBody
    public ResponseEntity<Object> checkUserLink(@NonNull @RequestBody UserAccessDetails userAccessDetails){
        boolean linkExists;
        try {
            linkExists = userManager.checkUserLink(userAccessDetails);
        }
        catch (Exception e){
            System.out.println("Exception while checking a user link: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if(linkExists)
            return ResponseEntity.ok("Link between the provided users exist.");
        else
            return ResponseEntity.ok("No link between the provided users exist.");
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> linkUsers(@NonNull @RequestBody UserAccessDetails userAccessDetails){
        try {
            userManager.linkUsers(userAccessDetails);
        }
        catch (Exception e){
            Logger.getLogger("Exception while linking users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Users linked successfully.");
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<Object> unlinkUsers(@NonNull @RequestBody UserAccessDetails userAccessDetails){
        try {
            userManager.unlinkUsers(userAccessDetails);
        }
        catch (Exception e){
            Logger.getLogger("Exception while unlinking users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Users unlinked successfully.");
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<Object> unlinkUsers(@NonNull @PathVariable Integer userId){
        List<User> userList;
        try {
            userList = userManager.getUsersLinkedTo(userId);
        }
        catch (Exception e){
            Logger.getLogger("Exception while listing linked users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(userList);
    }
}
