package com.cowrycode.smileapp.domains;

import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackerEntity extends BaseEntity  {
   // Long identifier;
    String trackerIdentifier; // This will be date
    LocalDate date;
    int targetValue; // Daily Target for user
    int achievedScore;
    boolean submittedDailyQuestionnaire;
    double todayAccumulatedSpentTime;
    double todayAccumulatedSmileDuration;

    @OneToMany
    List<SmileGramMoodEntity> smilegramlist;
    @OneToMany
    List<PocketBuddyMoodEntity> pocketbuddylist;
    @OneToMany
    List<TribeMoodEntity> myTribeList;
}
