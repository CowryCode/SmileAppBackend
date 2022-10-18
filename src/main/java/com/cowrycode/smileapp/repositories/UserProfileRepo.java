package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfileEntity, Long> {
    UserProfileEntity findByidentifier(Long phonenumber);
    UserProfileEntity findByphonenumber(String phonenumber);
}
