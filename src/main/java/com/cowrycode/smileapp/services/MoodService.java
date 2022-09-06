package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;

public interface MoodService {
    SmileGramMoodDTO saveSmileGramMood(SmileGramMoodDTO smileGramMoodDTO);
    PocketBuddyMoodDTO savePocketBuddyMood(PocketBuddyMoodDTO pocketBuddyMoodDTO);
    TribeMoodDTO saveTribemood(TribeMoodDTO tribeMoodDTO);
}
