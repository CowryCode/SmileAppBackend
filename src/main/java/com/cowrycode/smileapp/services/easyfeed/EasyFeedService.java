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
    boolean saveWeigthtData(WeightDataDTO weightDataDTO);
}
