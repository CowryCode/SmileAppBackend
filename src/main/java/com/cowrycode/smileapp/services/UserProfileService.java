package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;

import javax.servlet.http.HttpServletRequest;

public interface UserProfileService  {
    UserProfileDTO saveUserprofile(UserProfileDTO userProfileDTO);
    UserProfileDTO getprofile(HttpServletRequest request);
    UserProfileDTO savedDeviceID (Long userID, String deviceID);
    LeaderBoard sortPerformance(Long userID);
    Boolean pushNotification(Long userID, String title, String message);

}
