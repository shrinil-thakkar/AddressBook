package com.shrinil.addressbook.converter;

import com.shrinil.addressbook.entity.Address;
import com.shrinil.addressbook.model.AddressDetails;

public class AddressConverter {
    public static Address addressDetailsToAddress(Address address, AddressDetails addressDetails){
        /* cannot update user_id for an address,
        *  can update all the other fields */

        if(address == null)
            address = new Address();

        if(addressDetails.getAddressName() != null)
            address.setAddressName(addressDetails.getAddressName());
        if(addressDetails.getNotes() != null)
            address.setNotes(addressDetails.getNotes());
        if(addressDetails.getLatitude() != null)
            address.setLatitude(addressDetails.getLatitude());
        if(addressDetails.getLongitude() != null)
            address.setLongitude(addressDetails.getLongitude());
        if(addressDetails.getHouseNumber() != null)
            address.setHouseNumber(addressDetails.getHouseNumber());
        if(addressDetails.getApartment() != null)
            address.setApartment(addressDetails.getApartment());
        if(addressDetails.getLandmarks() != null)
            address.setLandmarks(addressDetails.getLandmarks());
        if(addressDetails.getLocality() != null)
            address.setLocality(addressDetails.getLocality());
        if(addressDetails.getCity() != null)
            address.setCity(addressDetails.getCity());
        if(addressDetails.getState() != null)
            address.setState(addressDetails.getState());
        if(addressDetails.getCountry() != null)
            address.setCountry(addressDetails.getCountry());
        if(addressDetails.getPinCode() != null)
            address.setPinCode(addressDetails.getPinCode());
        if(addressDetails.getPlaceType() != null)
            address.setPlaceType(addressDetails.getPlaceType());
        return address;
    }

    public static AddressDetails addressToAddressDetails(Address address) {
        return AddressDetails.builder()
                .addressId(address.getAddressId())
                .userId(address.getUser().getUserId())
                .addressName(address.getAddressName())
                .notes(address.getNotes())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .houseNumber(address.getHouseNumber())
                .apartment(address.getApartment())
                .landmarks(address.getLandmarks())
                .locality(address.getLocality())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pinCode(address.getPinCode())
                .placeType(address.getPlaceType())
                .build();
    }
}
