package com.cowrycode.smileapp.services.easyfeed.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyFeedUserStatus {
    private int todayBreastFeedingCount;
    private int todayPumpingCount;
    private int todayBottlingCount;
    private int numberOfDays;
    private List<FeedingData> historicData;
}
