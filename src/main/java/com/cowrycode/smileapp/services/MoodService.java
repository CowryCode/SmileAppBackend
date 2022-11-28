package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;

public interface MoodService {
    SmileGramMoodDTO saveSmileGramMood(SmileGramMoodDTO smileGramMoodDTO, String identifier);
    PocketBuddyMoodDTO savePocketBuddyMood(PocketBuddyMoodDTO pocketBuddyMoodDTO, String identifier);
    TribeMoodDTO saveTribemood(TribeMoodDTO tribeMoodDTO, String identifier);
}
