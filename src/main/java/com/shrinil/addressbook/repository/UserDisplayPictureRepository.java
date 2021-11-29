package com.shrinil.addressbook.repository;

import com.shrinil.addressbook.entity.UserDisplayPicture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDisplayPictureRepository extends CrudRepository<UserDisplayPicture, Integer> {
}
