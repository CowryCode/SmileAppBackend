package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;
import com.cowrycode.smileapp.domains.EmpathyRequestEntity;
import com.cowrycode.smileapp.domains.QuestionnaireBMIScaleEntity;
import com.cowrycode.smileapp.domains.TrackerEntity;
import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.mapper.EmpathyEntityMapper;
import com.cowrycode.smileapp.mapper.EmpathyRequestMapper;
import com.cowrycode.smileapp.mapper.QuestionnaireBMIScaleMapper;
import com.cowrycode.smileapp.mapper.UserProfileMapper;
import com.cowrycode.smileapp.models.EmpathyRequestDTO;
import com.cowrycode.smileapp.models.MyTribeMessageDTO;
import com.cowrycode.smileapp.models.QuestionnaireBMIScaleDTO;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.metamodels.GlobalProgress;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import com.cowrycode.smileapp.models.metamodels.PersonalProgress;
import com.cowrycode.smileapp.repositories.EmpathyRequestRepo;
import com.cowrycode.smileapp.repositories.QuestionnaireBMIScaleRepo;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepo userProfileRepo;
    private final FCMSenderService fcmSenderService;
    private final EmpathyRequestRepo empathyRequestRepo;
    private final MyTribeMessageService myTribeMessageService;
    private final QuestionnaireBMIScaleRepo questionnaireBMIScaleRepo;

    private UserProfileMapper userProfileMapper = UserProfileMapper.INSTANCE;
    private EmpathyEntityMapper empathyEntityMapper = EmpathyEntityMapper.INSTANCE;
    private EmpathyRequestMapper empathyRequestMapper = EmpathyRequestMapper.INSTANCE;
    private final QuestionnaireBMIScaleMapper questionnaireBMIScaleMapper = QuestionnaireBMIScaleMapper.INSTANCE;

    public UserProfileServiceImpl(UserProfileRepo userProfileRepo,
                                  FCMSenderService fcmSenderService,
                                  EmpathyRequestRepo empathyRequestRepo,
                                  MyTribeMessageService myTribeMessageService,
                                  QuestionnaireBMIScaleRepo questionnaireBMIScaleRepo) {
        this.userProfileRepo = userProfileRepo;
        this.fcmSenderService = fcmSenderService;
        this.empathyRequestRepo = empathyRequestRepo;
        this.myTribeMessageService = myTribeMessageService;
        this.questionnaireBMIScaleRepo = questionnaireBMIScaleRepo;
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
            String identifier = extractToken(request);
            if(identifier != null){
                UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
                UserProfileDTO profileDTO = userProfileMapper.EntitytoDTO(profile);
                profileDTO.setSmilegrammappoints(generateMapString(profile.getSmilegrampoint()));
                return profileDTO;
                //return userProfileMapper.EntitytoDTO(profile);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    String generateMapString(double smileGramPoints){
        if (smileGramPoints <= 0) return "0";
        int countrySize = (int) smileGramPoints % 175; // I75 is number of countries in the Map array on the app, we don't want an overflow
        String result = "0";
        for(int x = 1; x<= countrySize; x++ ){
            result = result + ","+x;
        }
        return result;
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
    public UserProfileDTO savedDeviceID(String userID, String deviceID) {
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
    public LeaderBoard sortPerformance(String userID) {
        try {
           UserProfileEntity profileDTO = userProfileRepo.findByidentifier(userID);
           if(profileDTO != null ){
               LeaderBoard leaderBoard = new LeaderBoard();
               List<UserProfileEntity> topUsers = userProfileRepo.getTopPerformers();
               /*GLOBAL TOP USERS*/
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

                   /*PERSONAL PROGRESS*/
                   List<TrackerEntity> trackers = sortTrackers(profileDTO.getDailytrackers());
                   ArrayList<PersonalProgress> personalProgressList = new ArrayList<>();

                   for(int j= 0; j < trackers.size(); j++){
                       personalProgressList.add(new PersonalProgress(trackers.get(j).getId().intValue(),trackers.get(j).getTargetValue(), trackers.get(j).getAchievedScore()));
                   }
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

    List<TrackerEntity> sortTrackers(List<TrackerEntity> trackers){
        PriorityQueue<TrackerEntity> prioority = new PriorityQueue<>(
                (n1,n2) -> n1.getTrackerIdentifier().compareToIgnoreCase(n2.getTrackerIdentifier())
        );

        for(int x = 0; x < trackers.size(); x++){
            prioority.add(trackers.get(x));
        }
        List<TrackerEntity> result = new ArrayList<>();
        while (!prioority.isEmpty()){
            result.add(prioority.poll());
        }

        return result;
    }

    @Override
    public Boolean pushNotification(String userID, String title, String message) {
        try {
            UserProfileEntity profileDTO = userProfileRepo.findByidentifier(userID);
            if(profileDTO != null){
                return fcmSenderService.sendPushnotification(title, message, profileDTO.getDeviceId());
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean requestEmpathicMessage(String userID, EmpathyRequestDTO message) {
        try {
            List<UserProfileEntity> profiles = userProfileRepo.getTribeMembers(userID);
            if(profiles != null){
                empathyRequestRepo.save(empathyEntityMapper.DTOtoEntity(message));
                for(int i = 0; i < profiles.size(); i++){
                    fcmSenderService.sendPushnotification("Tribe Call", "Someone needs your empathy, please show love", profiles.get(i).getDeviceId());
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
    public List<EmpathyRequestDTO> replyEmpathicMessage(String userID, EmpathyRequestDTO message) {
        /* The "sender ID" is the ID of the user the requested for the Tribe message Wile
         * RecieverID is the ID of the euser that sent this empathic message */
        try {
            EmpathyRequestEntity empathyRequestEntity = empathyRequestRepo.getReferenceById(message.getId());
            if(empathyRequestEntity != null){
                empathyRequestEntity.setRespondedUsersIDs(empathyRequestEntity.getRespondedUsersIDs() + "," + message.getReceiverID());

                MyTribeMessageDTO myTribeMessageDTO = new MyTribeMessageDTO();
                myTribeMessageDTO.setContent(message.getContent());
                myTribeMessageDTO.setSourceCountry(message.getSourceCountry());
                myTribeMessageDTO.setIsread(false);
                myTribeMessageDTO.setReceiverID(message.getSenderID());

                empathyRequestRepo.save(empathyRequestEntity);
                myTribeMessageService.saveTribeMessage(myTribeMessageDTO, message.getSenderID());
                notifyUser(message.getSenderID());
                return getTribeRequests(userID);
            }else {
                return getTribeRequests(userID);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void notifyUser(String userID){
        try{
            UserProfileEntity userProfileEntity = userProfileRepo.findByidentifier(userID);
            if(userProfileEntity != null && userProfileEntity.getDeviceId() != null)  fcmSenderService.sendPushnotification("I Care", "I sent you an empathic note, check the SmileApp", userProfileEntity.getDeviceId());
        }catch (Exception e){

        }
   }

    @Override
    public List<EmpathyRequestDTO> getTribeRequests(String userID) {
        try {
            List<EmpathyRequestEntity> requests = empathyRequestRepo.getUnrespondedMessages(userID);
            if(requests != null){
               return requests.stream().map(empathyRequestMapper::entityToDTO).collect(Collectors.toList());
            }else {
                return null;
            }
        }catch (Exception e){
            // e.printStackTrace();
            return null;
        }
    }

    @Override
    public ChatObjectModel sendChat(String userID, String chat) {
        System.out.println("GOT TO THIS POINT ::::::::::::::::: 2");
        try {
            UserProfileEntity profileEntity = userProfileRepo.findByidentifier(userID);
            System.out.println("GOT TO THIS POINT :::::::::::::::::: 3");
            List<List<Integer>> arr = new ArrayList<>();
            System.out.println("GOT TO THIS POINT ::::::::::::::::: 4");
            ChatObjectModel chatObjectModel = new ChatObjectModel();
            System.out.println("GOT TO THIS POINT :::::::::::::::::: 5");
            chatObjectModel.setChatContent(tokenArrayToString());
            System.out.println("GOT TO THIS POINT ::::::::::::::::::: 6");
            chatObjectModel.setChatHistory(converStringToInteger(tokenArrayToString()));
            System.out.println("GOT TO THIS POINT :::::::::::::::::::: 7");
            return  chatObjectModel;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    List<List<Integer>> converStringToInteger(String history){
        if(history == null ) return null;

          String[] chatHistoryArry = history.split("TTT");

        List<Integer> comments = new ArrayList<>();
        List<List<Integer>> historyArr = new ArrayList<>();

        for (int x = 0; x < chatHistoryArry.length; x++){
            System.out.println("COVERSION NUMBER : " + x);
            String[] commentToken = chatHistoryArry[x].split(",");
            for(int y = 0; y < commentToken.length ; y++){
                System.out.println("COVERSION NUMBER : " + x);
                Integer value;
                try {
                    value = Integer.valueOf(commentToken[y]);
                    if(value != null){
                        comments.add(value);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
            historyArr.add(comments);
            comments = new ArrayList<>();// RE-initialize
        }
     return historyArr;
    }

    private String tokenArrayToString(){
        System.out.println("GOT TO 5A");
        List<List<Integer>> sampleTokenArray = new ArrayList<>();
        List<Integer> ar1 = new ArrayList<>();
        ar1.add(1);
        ar1.add(2);
        ar1.add(3);
        List<Integer> ar2 = new ArrayList<>();
        ar2.add(4);
        ar2.add(5);
        ar2.add(6);
        List<Integer> ar3 = new ArrayList<>();
        ar3.add(7);
        ar3.add(8);
        ar3.add(9);
        sampleTokenArray.add(ar1);
        sampleTokenArray.add(ar2);
        sampleTokenArray.add(ar3);
        String chatHistory = "";
        String commentTokens = "";

        for(int x = 0; x < sampleTokenArray.size(); x++){
            System.out.println("GOT TO 5A : " + x);
            List<Integer> comments = sampleTokenArray.get(x);
            for(int y = 0; y < comments.size(); y++){
                System.out.println("GOT TO 5B : " + y);
                commentTokens = commentTokens + comments.get(y) + ",";
            }
            chatHistory = chatHistory + commentTokens + "TTT";
            commentTokens = ""; // Re-Initialize
        }
        return chatHistory;
    }



    @Override
    public QuestionnaireBMIScaleDTO saveBMIScale(String userID, QuestionnaireBMIScaleDTO questionnaireBMIScaleDTO) {
        try{
            UserProfileEntity userProfileEntity = userProfileRepo.findByidentifier(userID);
            if(userProfileEntity != null){
                List<QuestionnaireBMIScaleEntity> BMIScales = userProfileEntity.getDailyquestionnaires();
                if(BMIScales == null){
                    BMIScales = new ArrayList<>();
                }
                QuestionnaireBMIScaleEntity savedBMIScale = questionnaireBMIScaleRepo.save(questionnaireBMIScaleMapper.dtoToEntity(questionnaireBMIScaleDTO));
                BMIScales.add(savedBMIScale);
                userProfileEntity.setDailyquestionnaires(BMIScales);
                userProfileRepo.save(userProfileEntity);
                return questionnaireBMIScaleMapper.entityToDTO(savedBMIScale);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
