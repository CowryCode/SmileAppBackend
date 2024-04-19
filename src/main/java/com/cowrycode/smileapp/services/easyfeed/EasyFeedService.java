package com.cowrycode.smileapp.services.easyfeed;

import com.cowrycode.smileapp.models.easyfeed.*;
import com.cowrycode.smileapp.services.easyfeed.utilities.LeaderBoard;

public interface EasyFeedService {
    void saveMilkData(BreastMilkDataDTO breastMilkDataDTO);
    //LeaderBoard getLeaderBoard(Long userID);
    LeaderBoard getLeaderBoard(String userID);
    boolean saveFeedBack(FeedBackDTO feedBackDTO);
    boolean saveHeightData(HeightDataDTO heightDataDTO);
    boolean saveJournalData(JournalDataDTO journalDataDTO);
    boolean saveEasyFeedUserProfile(EasyFeedUserprofileDTO easyFeedUserprofileDTO);
    EasyFeedUserprofileDTO getEasyFeedUserProfile(String userID);
    boolean saveWeigthtData(WeightDataDTO weightDataDTO);
    void saveDeviceID(String userID, String deviceID);
    boolean sendPushnotification();

    LastFeeding getLastFeeding(String userID);

    void sendEmail(String receiver, String msg);
}
