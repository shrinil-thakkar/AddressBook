package com.shrinil.addressbook.repository;

import com.shrinil.addressbook.entity.UserAccess;
import com.shrinil.addressbook.model.UserLinkId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessRepository extends CrudRepository<UserAccess, UserLinkId> {
}