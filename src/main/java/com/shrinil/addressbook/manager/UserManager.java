package com.shrinil.addressbook.manager;

import com.shrinil.addressbook.converter.UserConverter;
import com.shrinil.addressbook.entity.Address;
import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.entity.UserDisplayPicture;
import com.shrinil.addressbook.exception.UserDisplayPictureNotFoundException;
import com.shrinil.addressbook.exception.UserNotFoundException;
import com.shrinil.addressbook.model.UserDetails;
import com.shrinil.addressbook.repository.UserDisplayPictureRepository;
import com.shrinil.addressbook.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDisplayPictureRepository userDpRepository;

    public User createUser(@NonNull final User user){
        return userRepository.save(user);
    }

    public User updateUserDetails(
            @NonNull Integer userId,
            @NonNull final UserDetails userDetails,
            MultipartFile multipartFile){
        User user = getUser(userId);
        UserDisplayPicture userDp = new UserDisplayPicture();
        UserConverter.userDetailsToUser(user, userDetails, userDp, multipartFile);

        userDp = userDpRepository.save(userDp);
        user.setDisplayPictureId(userDp.getDpId());
        return userRepository.save(user);
    }

    public void updateUser(@NonNull final User user){
        userRepository.save(user);
    }

    public User getUser(@NonNull final Integer userId){
        /* Gets the address if present, else throws UserNotFoundException */

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return user.get();
        else
            throw new UserNotFoundException(userId);
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public void deleteUser(@NonNull final Integer userId){
        User user = getUser(userId);
        userRepository.delete(user);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void addUserAddress(@NonNull User user, @NonNull final Address address){
        Set<Integer> addressList = user.getAddressList();
        addressList.add(address.getAddressId());
        user.setAddressList(addressList);

        userRepository.save(user);
    }

    public void removeUserAddress(@NonNull User user, @NonNull final Integer addressId){
        Set<Integer> addressList = user.getAddressList();
        addressList.remove(addressId);
        user.setAddressList(addressList);

        userRepository.save(user);
    }

    public Byte[] getUserDisplayPicture(@NonNull Integer userId){
        User user = getUser(userId);
        int dpId = user.getDisplayPictureId();
        Optional<UserDisplayPicture> dp = userDpRepository.findById(dpId);
        UserDisplayPicture userDp;
        if(dp.isPresent())
            userDp = dp.get();
        else{
            throw new UserDisplayPictureNotFoundException(userId);
        }
        return userDp.getDisplayPicture();
    }
}
