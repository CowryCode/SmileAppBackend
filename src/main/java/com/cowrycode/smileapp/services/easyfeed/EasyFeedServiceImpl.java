package com.cowrycode.smileapp.services.easyfeed;

import com.cowrycode.smileapp.config.CustomException;
import com.cowrycode.smileapp.domains.easyfeed.*;
import com.cowrycode.smileapp.mapper.easyfeed.BreastMilkDataMapper;
import com.cowrycode.smileapp.mapper.easyfeed.EasyFeedUserProfileMapper;
import com.cowrycode.smileapp.mapper.easyfeed.JournalDataMapper;
import com.cowrycode.smileapp.models.easyfeed.*;
import com.cowrycode.smileapp.repositories.easyfeed.*;
import com.cowrycode.smileapp.services.FCMSenderService;
import com.cowrycode.smileapp.services.easyfeed.utilities.EasyFeedUserStatus;
import com.cowrycode.smileapp.services.easyfeed.utilities.FeedingData;
import com.cowrycode.smileapp.services.easyfeed.utilities.FeedingRanking;
import com.cowrycode.smileapp.services.easyfeed.utilities.LeaderBoard;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EasyFeedServiceImpl implements EasyFeedService{
    private final BreastMilkDataRepo breastMilkDataRepo;

    private final FCMSenderService fcmSenderService;
    private final BreastMilkDataMapper mapper = BreastMilkDataMapper.INSTANCE;


    //    DATE          NAME    DATA
    Map<LocalDate, Map<String, FeedingData>> feedingDataMap;

    private final EasyFeedUserProfilerRepo easyFeedUserProfilerRepo;
    private final FeedBackRepo feedBackRepo;
    private final HeightDataRepo heightDataRepo;
    private final JournalDataRepo journalDataRepo;
    private final WeightDataRepo weightDataRepo;

    private JournalDataMapper journalDataMapper = JournalDataMapper.INSTANCE;
    private EasyFeedUserProfileMapper userProfileMapper = EasyFeedUserProfileMapper.INSTANCE;


    public EasyFeedServiceImpl(BreastMilkDataRepo breastMilkDataRepo,
                               EasyFeedUserProfilerRepo easyFeedUserProfilerRepo,
                               FeedBackRepo feedBackRepo,
                               HeightDataRepo heightDataRepo,
                               JournalDataRepo journalDataRepo,
                               WeightDataRepo weightDataRepo,
                               FCMSenderService fcmSenderService

                               ) {
        this.breastMilkDataRepo = breastMilkDataRepo;
        feedingDataMap = new HashMap<>();

        this.easyFeedUserProfilerRepo = easyFeedUserProfilerRepo;
        this.feedBackRepo = feedBackRepo;
        this.heightDataRepo = heightDataRepo;
        this.journalDataRepo = journalDataRepo;
        this.weightDataRepo = weightDataRepo;
        this.fcmSenderService = fcmSenderService;

    }

    @Override
    public void saveMilkData(BreastMilkDataDTO breastMilkDataDTO) {
        breastMilkDataRepo.save(mapper.DTOtoEntity(breastMilkDataDTO));
        //getLeaderBoard("admin1@gmail.com");
    }

    @Override
    public LeaderBoard getLeaderBoard(String userID) {
        List<BreastMilkData> bdata = breastMilkDataRepo.findAll();

        EasyFeedUserProfileDAO profile = easyFeedUserProfilerRepo.findEasyFeedUserProfileDAOByUserID(userID);

        bdata.stream().forEach((breastMilkData)->{
            System.out.println("THE NAME " + breastMilkData.getUserName());
            this.separateDate(breastMilkData);
        });
        System.out.println("*****************************************************");

        String currentUserID;
        if(profile != null){
            currentUserID = profile.getUserID();
        }else{
            currentUserID = null;
        }

        LeaderBoard result = organizeDataByUser(currentUserID);
        feedingDataMap.clear();
        return result;
    }
    private LeaderBoard organizeDataByUser(String userID){
        try{
            // AGGREGATE USER DATA
            EasyFeedUserStatus userStatus = new EasyFeedUserStatus();
            List<FeedingData> userFeedingData = new ArrayList<>();
            // AGGREGATE OTHER DATA
            Map<String, List<FeedingData>> allUsersData = new HashMap<>();

            System.out.println(":::::::::::::::::: DATA DETAILS OF USERS :::::::::::::::::");
            for (Map.Entry<LocalDate, Map<String, FeedingData>>  ent : feedingDataMap.entrySet()) {
                for (Map.Entry<String, FeedingData> data : ent.getValue().entrySet()) {
                    if(data.getValue().getUserID().equals(userID)){
                        if(ent.getKey().equals(LocalDate.now())){
                            userStatus.setTodayBreastFeedingCount(data.getValue().getBreastFeedingCount());
                            userStatus.setTodayBottlingCount(data.getValue().getBottlingCount());
                            userStatus.setTodayPumpingCount(data.getValue().getPumpingCount());
                        }
                        userFeedingData.add(data.getValue());
                    }
                    List<FeedingData> fd;
                    if(allUsersData.containsKey(data.getValue().getUserID())){
                        fd = allUsersData.get(data.getValue().getUserID());
                    }else {
                        fd = new ArrayList<>();
                    }
                    fd.add(data.getValue());
                    allUsersData.put(data.getValue().getUserID(), fd);
                }
            }
            userStatus.setHistoricData(userFeedingData);
           return getUsersAverage(userStatus, allUsersData);
        }catch (Exception e){
            throw new CustomException("Failed at organizing data by user");
        }
    }

    private LeaderBoard getUsersAverage(EasyFeedUserStatus userStatus, Map<String, List<FeedingData>> allUsersData ){
        List<FeedingRanking > usersAVG = new ArrayList<>();
        int bfCounting = 0;
        int bottleCounting = 0;
        int pumpingCounting = 0;

        for (Map.Entry<String, List<FeedingData>>  userData : allUsersData.entrySet()) {
            String username = "N/A";
            for(int x = 0; x < userData.getValue().size(); x++){
                bfCounting = bfCounting + userData.getValue().get(x).getBreastFeedingCount();
                bottleCounting = bottleCounting + userData.getValue().get(x).getBottlingCount();
                pumpingCounting = pumpingCounting + userData.getValue().get(x).getPumpingCount();
            }
            int bfAvg = 0;
            int bottleAvg = 0;
            int pumpAvg = 0;
            if(bfCounting > 0){
                bfAvg = (int) bfCounting / userData.getValue().size();
                username = userData.getValue().get(0).getUserName();
            }
            if(bottleCounting > 0){
                bottleAvg = (int) bottleCounting / userData.getValue().size();
                username = userData.getValue().get(0).getUserName();
            }
            if(pumpingCounting > 0){
                pumpAvg = (int) pumpingCounting / userData.getValue().size();
                username = userData.getValue().get(0).getUserName();
            }
            usersAVG.add(new FeedingRanking(userData.getKey(), username, bfAvg, bottleAvg, pumpAvg ));
        }
        return  rankUsers(userStatus, usersAVG);
    }

    private LeaderBoard rankUsers(EasyFeedUserStatus userStatus, List<FeedingRanking > usersAVG ){

        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.setUserStatus(userStatus);
        // Create a priority queue with a custom comparator
        PriorityQueue<FeedingRanking> bfRanking =
                new PriorityQueue<FeedingRanking>((a,b) ->  b.getBreastAvgFeedingRank() - a.getBreastAvgFeedingRank());
        bfRanking.addAll(usersAVG);
        ArrayList<FeedingRanking> bfRankingSorted = new ArrayList<>();
        while (!bfRanking.isEmpty()){
            bfRankingSorted.add(bfRanking.poll());
        }
        leaderBoard.setBreastFeedingRanking(bfRankingSorted);

        PriorityQueue<FeedingRanking> bottleRanking =
                new PriorityQueue<FeedingRanking>((a,b) ->  b.getBottlingAvgRank() - a.getBottlingAvgRank());
        bottleRanking.addAll(usersAVG);
        ArrayList<FeedingRanking> bottleRankingSorted = new ArrayList<>();
        while (!bottleRanking.isEmpty()){
            bottleRankingSorted.add(bottleRanking.poll());
        }
        leaderBoard.setBottlingRanking(bottleRankingSorted);

        PriorityQueue<FeedingRanking> pumpRanking =
                new PriorityQueue<FeedingRanking>((a,b) ->  b.getPumpingAvgRank() - a.getPumpingAvgRank());
        pumpRanking.addAll(usersAVG);
        ArrayList<FeedingRanking> pumpRankingSorted = new ArrayList<>();
        while (!pumpRanking.isEmpty()){
            pumpRankingSorted.add(pumpRanking.poll());
        }
        leaderBoard.setPumpingRanking(pumpRankingSorted);
        leaderBoard.setUserStatus(userStatus);

        //leaderBoard.setBreastFeedingRanking(new ArrayList<>(bfRanking));
        //leaderBoard.setBottlingRanking(new ArrayList<>(bottleRanking));
        //leaderBoard.setPumpingRanking(new ArrayList<>(pumpRanking));

        return leaderBoard;

    }

    private void separateDate(BreastMilkData breastMilkData){
        if(breastMilkData == null ) return;
        if (breastMilkData.getDateCreated() == null) return;

        LocalDate datecreated =  breastMilkData.getDateCreated().toLocalDate();

        Map<String, FeedingData> usersData;
        FeedingData userData;
        if(feedingDataMap.containsKey(datecreated)){
            usersData = feedingDataMap.get(datecreated);
            if(usersData.containsKey(breastMilkData.getUserID().trim())){
                userData = usersData.get(breastMilkData.getUserID().trim());
            }else {
                userData = new FeedingData();
            }
        }else {
            usersData = new HashMap<>();
            userData = new FeedingData();
        }
        userData.setUserID(breastMilkData.getUserID());
        userData.setUserName(breastMilkData.getUserName());
        userData.setDate(datecreated);

        if(breastMilkData.isIsbreasting()){
            userData.setBreastFeedingCount(userData.getBreastFeedingCount() + 1);
        }else if(breastMilkData.isIsbottling()) {
            userData.setBottlingCount(userData.getBottlingCount() + 1);
        } else if (breastMilkData.isIspumping()) {
            userData.setPumpingCount(userData.getPumpingCount() + 1);
        }
        usersData.put(breastMilkData.getUserID().trim(), userData);
        feedingDataMap.put(datecreated, usersData);
        // NEW ENDS HERE
    }
    @Override
    public boolean saveHeightData(HeightDataDTO heightDataDTO) {
        heightDataRepo.save(new HeightData(heightDataDTO.getUserID(), heightDataDTO.getHeight()));
        return true;
    }
    @Override
    public boolean saveWeigthtData(WeightDataDTO weightDataDTO) {
        weightDataRepo.save(new WeightData(weightDataDTO.getUserID(), weightDataDTO.getWeight()));
        return true;
    }
    @Override
    public boolean saveFeedBack(FeedBackDTO feedBackDTO) {
        feedBackRepo.save(new FeedBack(feedBackDTO.getUserID(),feedBackDTO.getFeedback()));
        return true;
    }
    @Override
    public boolean saveEasyFeedUserProfile(EasyFeedUserprofileDTO easyFeedUserprofileDTO) {
        easyFeedUserProfilerRepo.save(userProfileMapper.DTOtoDAO(easyFeedUserprofileDTO));
        return true;
    }

    @Override
    public EasyFeedUserprofileDTO getEasyFeedUserProfile(String userID) {
        EasyFeedUserProfileDAO profile = easyFeedUserProfilerRepo.findEasyFeedUserProfileDAOByUserID(userID);
        EasyFeedUserprofileDTO profileDto = userProfileMapper.DAOtoDTO(profile);
        profileDto.setLastFeeding(getLastFeeding(userID));
        return profileDto;
    }

    @Override
    public boolean saveJournalData(JournalDataDTO journalDataDTO) {
        journalDataRepo.save(new JournalData(journalDataDTO.getUserID(), journalDataDTO.getJournal()));
        return true;
    }
    @Override
    public void saveDeviceID(String userID, String deviceID) {
        System.out.println(" USER ID: " + userID);
        System.out.println(" DEVICE ID: " + deviceID);
        EasyFeedUserProfileDAO profile = easyFeedUserProfilerRepo.findEasyFeedUserProfileDAOByUserID(userID);
        profile.setDeviceID(deviceID);
        EasyFeedUserProfileDAO savedProfile = easyFeedUserProfilerRepo.save(profile);
        System.out.println(" DEVICE ID 2.0 : " + savedProfile.getDeviceID());
    }

    @Override
    public boolean sendPushnotification() {
        List<EasyFeedUserProfileDAO> users = easyFeedUserProfilerRepo.findAll();
        users.stream().forEach((user)->{
            fcmSenderService.sendPushnotification("Feeding Alert","Have you fed the baby today?",user.getDeviceID());
        });
        return true;
    }

    @Override
    public LastFeeding getLastFeeding(String userID) {
        LastFeeding lastFeeding = new LastFeeding();

        List<BreastMilkData> breastfeading = breastMilkDataRepo.findBreastMilkDataByUserIDAndIsbreasting(userID, true);
        List<BreastMilkData>  pumping = breastMilkDataRepo.findBreastMilkDataByUserIDAndIspumping(userID, true);
        List<BreastMilkData> bttle = breastMilkDataRepo.findBreastMilkDataByUserIDAndIsbottlingOrderByDateCreatedDesc(userID, true);



        lastFeeding.setBreastfeeding(mapper.entityToDTO(getLastBreatFeedingData(breastfeading)));
        lastFeeding.setPumping(mapper.entityToDTO(getLastBreatFeedingData(pumping)));
        lastFeeding.setBottling(mapper.entityToDTO(getLastBreatFeedingData(bttle)));
        return lastFeeding;
    }

    private BreastMilkData getLastBreatFeedingData(List<BreastMilkData> dataList ){
        Collections.sort(dataList, new Comparator<BreastMilkData>(){
            @Override
            public int compare(BreastMilkData o1, BreastMilkData o2) {
                return o2.getDateCreated().compareTo(o1.getDateCreated());
            }
        });
       return dataList.get(0);
    }

    @Override
    public void sendEmail(String receiver, String msg) {

        String senderEmail = "easyfeed24@gmail.com";
        String senderPassword = "Easyfeed2024#";

        // SMTP server configuration
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(receiver));

        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(senderEmail));
        message.setSubject("Mail Subject");

       // String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


}
