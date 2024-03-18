package com.cowrycode.smileapp.services.easyfeed;

import com.cowrycode.smileapp.models.easyfeed.BreastMilkDataDTO;
import com.cowrycode.smileapp.services.easyfeed.utilities.LeaderBoard;

public interface EasyFeedService {
    void saveMilkData(BreastMilkDataDTO breastMilkDataDTO);
    //LeaderBoard getLeaderBoard(Long userID);
    LeaderBoard getLeaderBoard(String userID);
}
