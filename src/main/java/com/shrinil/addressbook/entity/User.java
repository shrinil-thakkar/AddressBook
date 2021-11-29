package com.shrinil.addressbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "DISPLAY_PICTURE_ID")
    private Integer displayPictureId;

    @ElementCollection
    @JsonIgnore
    @Column(name = "ADDRESS_LIST")
    /* contains list of addresses owned by the user, the deleted addresses are removed from the list */
    private Set<Integer> addressList;


    //    @OneToMany(targetEntity = Address.class,
    //            cascade = CascadeType.ALL,
    //            fetch = FetchType.LAZY,
    //            mappedBy = "user")
    //    @ToString.Exclude
    //    private Set<Address> addressList;

    public User(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressList = new HashSet<>();
    }
}
