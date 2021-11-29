package com.shrinil.addressbook.controller;

import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.manager.UserLinkManager;
import com.shrinil.addressbook.model.UserLinkDetails;
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
public class UserLinkController {

    @Autowired
    private UserLinkManager userLinkManager;

    @PostMapping("/check")
    @ResponseBody
    public ResponseEntity<Object> checkUserLink(@NonNull @RequestBody UserLinkDetails userLinkDetails){
        boolean linkExists;
        try {
            linkExists = userLinkManager.checkUserLink(userLinkDetails);
        }
        catch (Exception e){
            System.out.println("Exception while checking a user link: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if(linkExists)
            return ResponseEntity.ok("Linked.");
        else
            return ResponseEntity.ok("Not linked.");
    }

    @PostMapping("/checkRequests")
    @ResponseBody
    public ResponseEntity<Object> checkUserLinkRequest(@NonNull @RequestBody UserLinkDetails userLinkDetails){
        boolean linkExists;
        try {
            linkExists = userLinkManager.checkUserLinkRequest(userLinkDetails);
        }
        catch (Exception e){
            System.out.println("Exception while checking a user link request: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if(linkExists)
            return ResponseEntity.ok("Request exists.");
        else
            return ResponseEntity.ok("No request exists.");
    }

    @PostMapping("/request")
    @ResponseBody
    public ResponseEntity<Object> requestLinkUsers(@NonNull @RequestBody UserLinkDetails userLinkDetails){
        try {
            userLinkManager.requestLinkUsers(userLinkDetails);
        }
        catch (Exception e){
            System.out.println("Exception while linking users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Users linked successfully.");
    }

    @PostMapping("/approve")
    @ResponseBody
    public ResponseEntity<Object> approveLinkUsers(@NonNull @RequestBody UserLinkDetails userLinkDetails){
        try {
            userLinkManager.approveLinkUsers(userLinkDetails);
        }
        catch (Exception e){
            System.out.println("Exception while linking users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Users linked successfully.");
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<Object> unlinkUsers(@NonNull @RequestBody UserLinkDetails userAccessDetails){
        try {
            userLinkManager.unlinkUsers(userAccessDetails);
        }
        catch (Exception e){
            Logger.getLogger("Exception while unlinking users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Users unlinked successfully.");
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<Object> getLinkedUsersById(@NonNull @PathVariable Integer userId){
        List<User> userList;
        try {
            userList = userLinkManager.getLinkedUsersById(userId);
        }
        catch (Exception e){
            Logger.getLogger("Exception while listing linked users: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(userList);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllUserLinks(){
        List<UserLinkDetails> userLinkDetails;
        try {
            userLinkDetails = userLinkManager.getAllUserLinks();
        }
        catch (Exception e){
            Logger.getLogger("Exception while listing all user links: " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(userLinkDetails);
    }
}
