package com.cowrycode.smileapp.models.easyfeed;

import com.cowrycode.smileapp.services.easyfeed.utilities.FeedingRanking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFeeding {
   private BreastMilkDataDTO breastfeeding;
   private BreastMilkDataDTO pumping;
   private BreastMilkDataDTO bottling;
}
