package com.shrinil.addressbook.repository;

import com.shrinil.addressbook.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress, Integer> {
}
