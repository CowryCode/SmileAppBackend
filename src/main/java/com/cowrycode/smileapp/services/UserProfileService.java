package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;
import com.cowrycode.smileapp.models.EmpathyRequestDTO;
import com.cowrycode.smileapp.models.QuestionnaireBMIScaleDTO;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserProfileService  {
    UserProfileDTO saveUserprofile(UserProfileDTO userProfileDTO);
    UserProfileDTO getprofile(HttpServletRequest request);
    UserProfileDTO savedDeviceID (String userID, String deviceID);
    LeaderBoard sortPerformance(String userID);
    Boolean pushNotification(String userID, String title, String message);
    Boolean requestEmpathicMessage(String userID, EmpathyRequestDTO message);
    List<EmpathyRequestDTO> replyEmpathicMessage(String userID, EmpathyRequestDTO message);
    List<EmpathyRequestDTO> getTribeRequests(String userID);
    QuestionnaireBMIScaleDTO saveBMIScale(String userID, QuestionnaireBMIScaleDTO questionnaireBMIScaleDTO);
    ChatObjectModel sendChat(String userID, String chat);

    List<UserProfileDTO> getALlUsers(String user);
    List<UserProfileDTO> getALlUsersNotDownloadedApp(String user);
    List<UserProfileDTO> getAllAppDownloadedUsers(String user);

    String getNextParticipantID(Long opinioID);

}
