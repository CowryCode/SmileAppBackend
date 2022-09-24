package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.mapper.UserProfileMapper;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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

    @Override
    public UserProfileDTO getprofile(HttpServletRequest request) {
        try{
            String phonenumberToken = extractToken(request);
            if(phonenumberToken != null){
                UserProfileEntity profile = userProfileRepo.findByphonenumber(phonenumberToken);
                return userProfileMapper.EntitytoDTO(profile);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    private String extractToken(HttpServletRequest request){
        try{
            String header = request.getHeader("Authorization" );
            String token;
            if(header.startsWith("Bearer ")){
                token = header.substring(7).trim();
                return token;
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public UserProfileDTO savedDeviceID(Long userID, String deviceID) {
        try {
            Optional<UserProfileEntity> profile = userProfileRepo.findById(userID);
            if(profile.isPresent()){
                UserProfileEntity profileEntity = profile.get();
                profileEntity.setDeviceId(deviceID);
                UserProfileEntity updatedprofile =  userProfileRepo.save(profileEntity);
                return userProfileMapper.EntitytoDTO(updatedprofile);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
