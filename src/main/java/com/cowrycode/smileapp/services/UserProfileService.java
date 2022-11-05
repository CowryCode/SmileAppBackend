package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.EmpathyRequestDTO;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserProfileService  {
    UserProfileDTO saveUserprofile(UserProfileDTO userProfileDTO);
    UserProfileDTO getprofile(HttpServletRequest request);
    UserProfileDTO savedDeviceID (Long userID, String deviceID);
    LeaderBoard sortPerformance(Long userID);
    Boolean pushNotification(Long userID, String title, String message);
    Boolean requestEmpathicMessage(Long userID, EmpathyRequestDTO message);
    List<EmpathyRequestDTO> getTribeRequests(Long userID);

}
