package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;
import com.cowrycode.smileapp.domains.TrackerEntity;
import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.mapper.EmpathyEntityMapper;
import com.cowrycode.smileapp.mapper.EmpathyRequestMapper;
import com.cowrycode.smileapp.mapper.UserProfileMapper;
import com.cowrycode.smileapp.models.EmpathyRequestDTO;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.metamodels.GlobalProgress;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import com.cowrycode.smileapp.models.metamodels.PersonalProgress;
import com.cowrycode.smileapp.repositories.EmpathyRequestRepo;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepo userProfileRepo;
    private final FCMSenderService fcmSenderService;
    private final EmpathyRequestRepo empathyRequestRepo;

    private UserProfileMapper userProfileMapper = UserProfileMapper.INSTANCE;
    private EmpathyEntityMapper empathyEntityMapper = EmpathyEntityMapper.INSTANCE;
    private EmpathyRequestMapper empathyRequestMapper = EmpathyRequestMapper.INSTANCE;

    public UserProfileServiceImpl(UserProfileRepo userProfileRepo,
                                  FCMSenderService fcmSenderService,
                                  EmpathyRequestRepo empathyRequestRepo) {
        this.userProfileRepo = userProfileRepo;
        this.fcmSenderService = fcmSenderService;
        this.empathyRequestRepo = empathyRequestRepo;
    }

    @Override
    public UserProfileDTO saveUserprofile(UserProfileDTO userProfileDTO) {
        try {
            UserProfileEntity savedProfile = userProfileRepo.findByidentifier(userProfileDTO.getIdentifier());
            if(savedProfile == null){
                savedProfile = userProfileRepo.save(userProfileMapper.DTOtoEntity(userProfileDTO));
                return userProfileMapper.EntitytoDTO(savedProfile);
            }else {
                return userProfileMapper.EntitytoDTO(savedProfile);
            }

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
            UserProfileEntity profile = userProfileRepo.findByidentifier(userID);
            if(profile != null){
               // UserProfileEntity profileEntity = profile.get();
                profile.setDeviceId(deviceID);
                UserProfileEntity updatedprofile =  userProfileRepo.save(profile);
                return userProfileMapper.EntitytoDTO(updatedprofile);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public LeaderBoard sortPerformance(Long userID) {
        try {
           UserProfileEntity profileDTO = userProfileRepo.findByidentifier(userID);
           if(profileDTO != null ){
               LeaderBoard leaderBoard = new LeaderBoard();
               List<UserProfileEntity> topUsers = userProfileRepo.getTopPerformers();
               if(topUsers != null && topUsers.size() > 0){
                   List<GlobalProgress> globalProgresses = new ArrayList<>();
                   double totalValue = 0;
                   for (int i = 0; i < topUsers.size(); i++){
                       totalValue += topUsers.get(i).getAccumulatedValue();
                       globalProgresses.add(new GlobalProgress(topUsers.get(i).getName(),topUsers.get(i).getAccumulatedValue(), 0.0));
                   }

                   for (int x = 0; x < globalProgresses.size(); x++){
                       double av = globalProgresses.get(x).getAcumulatedValue();
                       double globaPerc = Math.round ((av/totalValue) * 100);
                       GlobalProgress progress = globalProgresses.get(x);
                       progress.setGlobalpercent(globaPerc);
                       globalProgresses.set(x, progress );
                   }

                   List<TrackerEntity> trackers =  profileDTO.getDailytrackers();
                   ArrayList<PersonalProgress> personalProgressList = new ArrayList<>();

                   for(int j= 0; j < trackers.size(); j++){
                       personalProgressList.add(new PersonalProgress(trackers.get(j).getId().intValue(),trackers.get(j).getTargetValue(), trackers.get(j).getAchievedScore()));
                   }
                   Collections.sort(personalProgressList);
                   leaderBoard.setPersonalProgresses(personalProgressList);
                   leaderBoard.setGlobalProgresses(globalProgresses);
                   return leaderBoard;
               }else {
                   return null;
               }

           }else {
               return null;
           }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean pushNotification(Long userID, String title, String message) {
        try {
            UserProfileEntity profileDTO = userProfileRepo.findByidentifier(userID);
            if(profileDTO != null){
                return fcmSenderService.sendPushnotification("Title", "This is a test", profileDTO.getDeviceId());
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean requestEmpathicMessage(Long userID, EmpathyRequestDTO message) {
        try {
            List<UserProfileEntity> profiles = userProfileRepo.getTribeMembers(userID);
            if(profiles != null){
                empathyRequestRepo.save(empathyEntityMapper.DTOtoEntity(message));
                for(int i = 0; i < profiles.size(); i++){
                    System.out.println("Sent to : " + profiles.get(i).getId() );
                    fcmSenderService.sendPushnotification("Tribe Call", "Someone feels down", profiles.get(i).getDeviceId());
                }
                return true;

            }else {
                return false;
            }
        }catch (Exception e){
          //  e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EmpathyRequestDTO> getTribeRequests(Long userID) {
        try {
            List<EmpathyRequestEntity> requests = empathyRequestRepo.getUnrespondedMessages(userID);
            if(requests != null){
               return requests.stream().map(empathyRequestMapper::entityToDTO).collect(Collectors.toList());
            }else {
                return null;
            }
        }catch (Exception e){
             e.printStackTrace();
            return null;
        }
    }
}
