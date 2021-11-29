package com.shrinil.addressbook.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "USER_ID", nullable = false,
            foreignKey = @ForeignKey(
                    name="FK_USER_ADDRESS",
                    foreignKeyDefinition = "FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE"
            ))
    private User user;

    @Column(name = "ADDRESS_NAME", length = 20)
    private String addressName;

    @Column(name = "notes")
    private String notes;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;
    /* Also includes block num, flat num, floor num etc. details */

    @Column(name = "APARTMENT")
    private String apartment;

    @ElementCollection
    @Column(name = "LANDMARKS")
    private List<String> landmarks;

    @Column(name = "LOCALITY")
    private String locality;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "PINCODE", nullable = false)
    private String pinCode;

    @Column(name = "PLACE_TYPE")
    private String placeType;
}
