package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepo extends JpaRepository<UserProfileEntity, Long>, UserProfileRepoCustom {
    UserProfileEntity findByIdentifierOrName(String identifier, String name);

    List<UserProfileEntity> findAllByDeviceIdIsNotNull();
    List<UserProfileEntity> findAllByDeviceIdIsNull();
    UserProfileEntity findByphonenumber(String phonenumber);
}
