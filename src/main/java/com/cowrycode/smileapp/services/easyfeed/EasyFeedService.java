package com.cowrycode.smileapp.services.easyfeed;

import com.cowrycode.smileapp.models.easyfeed.BreastMilkDataDTO;
import com.cowrycode.smileapp.services.easyfeed.utilities.EasyFeedStatus;

public interface EasyFeedService {
    void saveMilkData(BreastMilkDataDTO breastMilkDataDTO);
    EasyFeedStatus getStatus(String userID);
}
