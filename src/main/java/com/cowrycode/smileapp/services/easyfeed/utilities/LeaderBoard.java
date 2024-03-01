package com.cowrycode.smileapp.services.easyfeed.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoard {
    private EasyFeedUserStatus userStatus;
    private List<FeedingRanking> breastFeedingRanking;
    private List<FeedingRanking> bottlingRanking;
    private List<FeedingRanking> pumpingRanking;
}
