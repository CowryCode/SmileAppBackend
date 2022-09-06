package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.mapper.UserProfileMapper;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepo userProfileRepo;

    private UserProfileMapper userProfileMapper = UserProfileMapper.INSTANCE;

    public UserProfileServiceImpl(UserProfileRepo userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }

    @Override
    public UserProfileDTO saveUserprofile(UserProfileDTO userProfileDTO) {
        try {
            UserProfileEntity savedProfile = userProfileRepo.save(userProfileMapper.DTOtoEntity(userProfileDTO));
            return userProfileMapper.EntitytoDTO(savedProfile);
        }catch (Exception e){
            return null;
        }
    }
}
