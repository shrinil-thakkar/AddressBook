package com.shrinil.addressbook.manager;

import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.entity.UserLink;
import com.shrinil.addressbook.entity.UserLink.LinkStatus;
import com.shrinil.addressbook.exception.IncorrectAttributeException;
import com.shrinil.addressbook.model.UserLinkDetails;
import com.shrinil.addressbook.model.UserLinkId;
import com.shrinil.addressbook.repository.UserLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserLinkManager {
    @Autowired
    UserLinkRepository userAccessRepository;

    @Autowired
    UserManager userManager;

    public boolean checkUserLink(UserLinkDetails userAccessDetails){
        UserLinkId userLinkId = getUserLinkId(userAccessDetails);
        UserLink userLink = userAccessRepository.findById(userLinkId).orElse(null);
        return userLink != null && userLink.getLinkStatus().equals(LinkStatus.APPROVED);
    }

    public boolean checkUserLinkRequest(UserLinkDetails userAccessDetails){
        UserLinkId userLinkId = getUserLinkId(userAccessDetails);
        UserLink userLink = userAccessRepository.findById(userLinkId).orElse(null);
        return userLink != null && userLink.getLinkStatus().equals(LinkStatus.REQUESTED);
    }

    public void requestLinkUsers(UserLinkDetails userAccessDetails){
        // User Validation
        UserLink userLink = getUserLink(userAccessDetails);
        userAccessRepository.save(userLink);
    }

    public void approveLinkUsers(UserLinkDetails userAccessDetails){
        // User Validation
        UserLinkId userLinkId = getUserLinkId(userAccessDetails);
        UserLink userLink = userAccessRepository.findById(userLinkId).orElse(null);

        if(userLink == null)
            throw new IncorrectAttributeException("Follow request must exist in order to approve.");

        userLink.setLinkStatus(LinkStatus.APPROVED);
        userAccessRepository.save(userLink);
    }

    public void unlinkUsers(UserLinkDetails userAccessDetails){
        UserLinkId userLinkId = getUserLinkId(userAccessDetails);
        userAccessRepository.findById(userLinkId).ifPresent(user_access -> userAccessRepository.delete(user_access));
    }

    public List<User> getLinkedUsersById(Integer userId) {
        List<User> userList = new ArrayList<>();
        for(UserLink userLink : userAccessRepository.findAll()){
            if(Objects.equals(userLink.getUser1().getUserId(), userId) &&
                    userLink.getLinkStatus().equals(LinkStatus.APPROVED))
                userList.add(userLink.getUser2());
        }
        return userList;
    }

    public List<UserLinkDetails> getAllUserLinks() {
        List<UserLinkDetails> userDetailsList = new ArrayList<>();
        for(UserLink userLink : userAccessRepository.findAll()){
            UserLinkDetails userLinkDetails = new UserLinkDetails(userLink.getUser1().getUserId(), userLink.getUser2().getUserId());
            userDetailsList.add(userLinkDetails);
        }
        return userDetailsList;
    }

    public UserLink getUserLink(UserLinkDetails userLinkDetails){
        Integer userId1 = userLinkDetails.getUser1();
        Integer userId2 = userLinkDetails.getUser2();

        // Validated if both userIds are different
        if(userId1.equals(userId2))
            throw new IncorrectAttributeException("Cannot have a link between same users");

        // Check if the users are valid
        User user1 = userManager.getUser(userId1);
        User user2 = userManager.getUser(userId2);

        return new UserLink(user1, user2, UserLink.LinkStatus.REQUESTED);
    }

    public UserLinkId getUserLinkId(UserLinkDetails userLinkDetails){
        Integer userId1 = userLinkDetails.getUser1();
        Integer userId2 = userLinkDetails.getUser2();

        // Validated if both userIds are different
        if(userId1.equals(userId2))
            throw new IncorrectAttributeException("Cannot have a link between same users");

        // Check if the users are valid
        User user1 = userManager.getUser(userId1);
        User user2 = userManager.getUser(userId2);

        return new UserLinkId(user1.getUserId(), user2.getUserId());
    }
}
