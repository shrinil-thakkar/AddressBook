package com.shrinil.addressbook.repository;

import com.shrinil.addressbook.entity.UserLink;
import com.shrinil.addressbook.model.UserLinkId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLinkRepository extends CrudRepository<UserLink, UserLinkId> {
}