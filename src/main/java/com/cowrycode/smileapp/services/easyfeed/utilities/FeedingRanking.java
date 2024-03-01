package com.cowrycode.smileapp.services.easyfeed.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedingRanking {
    private String userID;
    private int breastAvgFeedingRank;
    private int bottlingAvgRank;
    private int pumpingAvgRank;
}
