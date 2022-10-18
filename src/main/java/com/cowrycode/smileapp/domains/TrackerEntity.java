package com.cowrycode.smileapp.domains;

import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    @OneToMany
    List<SmileGramMoodEntity> smilegramlist;
    @OneToMany
    List<PocketBuddyMoodEntity> pocketbuddylist;
    @OneToMany
    List<TribeMoodEntity> myTribeList;
}
