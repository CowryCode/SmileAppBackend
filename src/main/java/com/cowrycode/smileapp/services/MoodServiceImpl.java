package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.TrackerEntity;
import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import com.cowrycode.smileapp.mapper.featuresmood.PocketBuddyMoodMapper;
import com.cowrycode.smileapp.mapper.featuresmood.SmileGramMoodMapper;
import com.cowrycode.smileapp.mapper.featuresmood.TribeMoodMapper;
import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;
import com.cowrycode.smileapp.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService {
    private final SmileGramMoodRepo smileGramMoodRepo;
    private final PocketBuddyMoodRepo pocketBuddyMoodRepo;
    private final TribeMoodRepo tribeMoodRepo;
    private final TrackerRepo trackerRepo;
    private final UserProfileRepo userProfileRepo;

    private PocketBuddyMoodMapper pocketBuddyMoodMapper = PocketBuddyMoodMapper.INSTANCE;
    private TribeMoodMapper tribeMoodMapper = TribeMoodMapper.INSTANCE;
    private SmileGramMoodMapper smileGramMoodMapper = SmileGramMoodMapper.INSTANCE;

    public MoodServiceImpl(SmileGramMoodRepo smileGramMoodRepo,
                           PocketBuddyMoodRepo pocketBuddyMoodRepo,
                           TribeMoodRepo tribeMoodRepo,
                           TrackerRepo trackerRepo,
                           UserProfileRepo userProfileRepo
    ) {
        this.smileGramMoodRepo = smileGramMoodRepo;
        this.pocketBuddyMoodRepo = pocketBuddyMoodRepo;
        this.tribeMoodRepo = tribeMoodRepo;
        this.trackerRepo = trackerRepo;
        this.userProfileRepo = userProfileRepo;
    }

    @Override
    public SmileGramMoodDTO saveSmileGramMood(SmileGramMoodDTO smileGramMoodDTO, Long identifier) {
        try {
           UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
           if(profile != null){
               TrackerEntity tracker = profile.getTrackerEntity();
               List<SmileGramMoodEntity> grams;
               if(tracker == null){
                   tracker =  trackerRepo.save(new TrackerEntity());
                   grams = new ArrayList<>();
               }else {
                   grams = profile.getTrackerEntity().getSmilegramlist();
                   if(grams == null){
                       grams = new ArrayList<>();
                   }
               }
               SmileGramMoodEntity saveedSmileGram = smileGramMoodRepo.save(smileGramMoodMapper.DTOtoEntity(smileGramMoodDTO));
               grams.add(saveedSmileGram);
               tracker.setSmilegramlist(grams);
               tracker.setDate(LocalDate.now());
               tracker.setIdentifier(identifier);
               trackerRepo.save(tracker);
               profile.setTrackerEntity(tracker);
               userProfileRepo.save(profile);
                   return smileGramMoodMapper.EntityToDTO(saveedSmileGram);
           } else {
               return null;
           }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PocketBuddyMoodDTO savePocketBuddyMood(PocketBuddyMoodDTO pocketBuddyMoodDTO, Long identifier) {
        try {
            UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
            if(profile != null){
                TrackerEntity tracker = profile.getTrackerEntity();
                List<PocketBuddyMoodEntity> pocketbuddies;
                if(tracker == null){
                    tracker =  trackerRepo.save(new TrackerEntity());
                    pocketbuddies = new ArrayList<>();
                }else {
                    pocketbuddies = profile.getTrackerEntity().getPocketbuddylist();
                    if(pocketbuddies == null){
                        pocketbuddies = new ArrayList<>();
                    }
                }
                PocketBuddyMoodEntity saveedPocketBuddy = pocketBuddyMoodRepo.save(pocketBuddyMoodMapper.DTOtoEntity(pocketBuddyMoodDTO));
                pocketbuddies.add(saveedPocketBuddy);
                tracker.setPocketbuddylist(pocketbuddies);
                tracker.setDate(LocalDate.now());
                tracker.setIdentifier(identifier);
                trackerRepo.save(tracker);
                profile.setTrackerEntity(tracker);
                userProfileRepo.save(profile);
                return pocketBuddyMoodMapper.EntityToDTO(saveedPocketBuddy);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TribeMoodDTO saveTribemood(TribeMoodDTO tribeMoodDTO, Long identifier) {
        try {
            UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
            if(profile != null){
                TrackerEntity tracker = profile.getTrackerEntity();
                List<TribeMoodEntity> tribemoods;
                if(tracker == null){
                    tracker =  trackerRepo.save(new TrackerEntity());
                    tribemoods = new ArrayList<>();
                }else {
                    tribemoods = profile.getTrackerEntity().getMyTribeList();
                    tribemoods = profile.getTrackerEntity().getMyTribeList();
                    if(tribemoods == null){
                        tribemoods = new ArrayList<>();
                    }
                }
                TribeMoodEntity saveedTribemoood = tribeMoodRepo.save(tribeMoodMapper.DTOtoEntity(tribeMoodDTO));
                tribemoods.add(saveedTribemoood);
                tracker.setMyTribeList(tribemoods);
                tracker.setDate(LocalDate.now());
                tracker.setIdentifier(identifier);
                trackerRepo.save(tracker);
                profile.setTrackerEntity(tracker);
                userProfileRepo.save(profile);
                return tribeMoodMapper.EntitytoDTO(saveedTribemoood);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
