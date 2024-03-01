package com.cowrycode.smileapp.services.easyfeed.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedingData {
    private String userID;
    private LocalDate date;
    private int breastFeedingCount;
    private int pumpingCount;
    private int bottlingCount;
}
