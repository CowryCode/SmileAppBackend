package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.UserProfileEntity;

import java.util.List;

public interface UserProfileRepoCustom {
    List<UserProfileEntity> getTopPerformers();
    List<UserProfileEntity> getTribeMembers(Long currentUser);
}
