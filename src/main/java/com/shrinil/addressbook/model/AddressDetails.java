package com.shrinil.addressbook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDetails {

    private Integer addressId;

    private Integer userId;

    private String addressName;

    private String notes;

    private Double latitude;

    private Double longitude;

    private String houseNumber;

    private String apartment;

    private List<String> landmarks;

    private String locality;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    private String placeType;
}
