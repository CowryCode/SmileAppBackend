package com.cowrycode.smileapp.models;


import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackEntityDTO {
    private Long id;
    String trackerIdentifier; // This will be date
    LocalDate date;
    int targetValue;
    int achievedScore; // This is number of country user have painted green per day
    List<SmileGramMoodEntity> smilegramlist;
    List<PocketBuddyMoodEntity> pocketbuddylist;
    List<TribeMoodEntity> myTribeList;

}
