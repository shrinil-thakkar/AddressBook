package com.shrinil.addressbook.manager;

import com.shrinil.addressbook.converter.UserConverter;
import com.shrinil.addressbook.exception.InternalError;
import com.shrinil.addressbook.model.UserDetails;
import com.shrinil.addressbook.repository.UserRepository;
import com.shrinil.addressbook.exception.UserNotFoundException;
import com.shrinil.addressbook.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public User createUser(@NonNull final User user){
        return userRepository.save(user);
    }

    public User updateUser(@NonNull Integer userId, @NonNull final UserDetails userDetails){
        User user = getUser(userId);
        User updatedUser = UserConverter.userDetailsToUser(user, userDetails);

        if(user == null)
            throw new InternalError();

        return userRepository.save(updatedUser);
    }


    public User getUser(@NonNull final Integer userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return user.get();
        else
            throw new UserNotFoundException("Account with the userId=" + userId + " does not exist.");
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public void deleteUser(@NonNull final Integer userId){
        if(userRepository.existsById(userId))
            userRepository.deleteById(userId);
        else
            throw new UserNotFoundException("Account with the userId=" + userId + " does not exist.");
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

}
