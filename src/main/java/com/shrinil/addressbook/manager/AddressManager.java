package com.shrinil.addressbook.manager;

import com.shrinil.addressbook.converter.AddressConverter;
import com.shrinil.addressbook.entity.Address;
import com.shrinil.addressbook.entity.User;
import com.shrinil.addressbook.exception.AddressNotFoundException;
import com.shrinil.addressbook.model.AddressDetails;
import com.shrinil.addressbook.repository.AddressRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressManager {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserManager userManager;

    public Address createAddress(
            @NonNull User user, @NonNull AddressDetails addressDetails){
        Address address = AddressConverter.addressDetailsToAddress(null, addressDetails);
        address.setUser(user);
        address = addressRepository.save(address);

        userManager.addUserAddress(user, address);

        return address;
    }

    public Address updateAddressDetails(
            @NonNull Integer addressId, @NonNull final AddressDetails addressDetails){
        Address address = getAddress(addressId);
        Address updatedAddress = AddressConverter.addressDetailsToAddress(address, addressDetails);
        return addressRepository.save(updatedAddress);
    }
    
    public Address getAddress(@NonNull final Integer addressId){
        Optional<Address> Address = addressRepository.findById(addressId);
        if(Address.isPresent())
            return Address.get();
        else
            throw new AddressNotFoundException(addressId);
    }

    public List<Address> getAllAddressForUser(User user){
        List<Address> addressList = new ArrayList<>();
        Set<Integer> addressIdList = user.getAddressList();
        addressRepository.findAllById(addressIdList).forEach(addressList::add);

        return addressList;
    }

    public void deleteAddressById(User user, Integer addressId){
        addressRepository.deleteById(addressId);
        userManager.removeUserAddress(user, addressId);
    }

    public void deleteAllAddressForUser(User user){
        Set<Integer> addressIdList = user.getAddressList();
        addressRepository.deleteAllById(addressIdList);

        addressIdList.clear();
        user.setAddressList(addressIdList);
        userManager.updateUser(user);
    }
}
