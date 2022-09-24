package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.UserProfileDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserProfileService  {
    UserProfileDTO saveUserprofile(UserProfileDTO userProfileDTO);
    UserProfileDTO getprofile(HttpServletRequest request);
    UserProfileDTO savedDeviceID (Long userID, String deviceID);
}
