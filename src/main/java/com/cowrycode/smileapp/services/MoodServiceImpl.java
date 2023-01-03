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
    public SmileGramMoodDTO saveSmileGramMood(SmileGramMoodDTO smileGramMoodDTO, String identifier) {
        System.out.println("IDENTIFIER IS : " + smileGramMoodDTO.getEndDate());
        try {
           UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
           if(profile != null){
               System.out.println("TRACKER GOT TO HERE 1");
               TrackerEntity tracker = trackerRepo.findBytrackerIdentifier(smileGramMoodDTO.getEndDate().toString());
               System.out.println("TRACKER GOT TO HERE 2");
               List<SmileGramMoodEntity> grams;
               if(tracker == null){
                   System.out.println("TRACKER GOT TO HERE 3");
                   TrackerEntity newtrackerEntity = new TrackerEntity();
                   newtrackerEntity.setTrackerIdentifier(smileGramMoodDTO.getEndDate().toString());
                  // tracker =  trackerRepo.save(new TrackerEntity());
                   tracker =  trackerRepo.save(newtrackerEntity);
                   tracker.setAchievedScore(smileGramMoodDTO.getCountrycount());
                   grams = new ArrayList<>();
               }else {
                   System.out.println("TRACKER GOT TO HERE 4");
                   grams = tracker.getSmilegramlist();
                   if(grams == null){
                       grams = new ArrayList<>();
                   }

                   int achievedPoints = tracker.getAchievedScore();

                   achievedPoints = achievedPoints + smileGramMoodDTO.getCountrycount();
                   tracker.setAchievedScore(achievedPoints);
               }
               SmileGramMoodEntity saveedSmileGram = smileGramMoodRepo.save(smileGramMoodMapper.DTOtoEntity(smileGramMoodDTO));
               grams.add(saveedSmileGram);
               tracker.setSmilegramlist(grams);
               tracker.setDate(smileGramMoodDTO.getEndDate());
               trackerRepo.save(tracker);
              // profile.setTrackerEntity(tracker);
               List<TrackerEntity> trackerEntityList = profile.getDailytrackers();
               if(trackerEntityList == null ){
                   trackerEntityList = new ArrayList<>();
                   trackerEntityList.add(tracker);
                   profile.setDailytrackers(trackerEntityList);
               }else {
                   if(!trackerEntityList.contains(tracker)){
                       trackerEntityList.add(tracker);
                       profile.setDailytrackers(trackerEntityList);
                   }
               }

               // INCREAMENT THE OVERAL SMILE DURATION OF USER (Sec)
               double accumulatedValue = profile.getAccumulatedValue();
               if(accumulatedValue > 0){
                   accumulatedValue = accumulatedValue + smileGramMoodDTO.getSmileduration();
               }else {
                   accumulatedValue = smileGramMoodDTO.getSmileduration();
               }

               // INCREAMENT NUMBER OF COUNTRIES PAINTED GREEN
               double smilegramPoints = profile.getSmilegrampoints();
               if(smilegramPoints > 0){
                   smilegramPoints = smilegramPoints + smileGramMoodDTO.getCountrycount();
               }else {
                   smilegramPoints = smileGramMoodDTO.getCountrycount();
               }

               profile.setAccumulatedValue(accumulatedValue);
               profile.setSmilegrampoints(smilegramPoints);
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
    public PocketBuddyMoodDTO savePocketBuddyMood(PocketBuddyMoodDTO pocketBuddyMoodDTO, String identifier) {
        try {
            UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
            if(profile != null){
               // TrackerEntity tracker = profile.getTrackerEntity();
                TrackerEntity tracker = trackerRepo.findBytrackerIdentifier(pocketBuddyMoodDTO.getEndDate().toString());
                List<PocketBuddyMoodEntity> pocketbuddies;
                if(tracker == null){
                    TrackerEntity newtrackerEntity = new TrackerEntity();
                    newtrackerEntity.setTrackerIdentifier(pocketBuddyMoodDTO.getEndDate().toString());
                    // tracker =  trackerRepo.save(new TrackerEntity());
                    tracker =  trackerRepo.save(newtrackerEntity);
                    pocketbuddies = new ArrayList<>();
                }else {
                  //  pocketbuddies = profile.getTrackerEntity().getPocketbuddylist();
                    pocketbuddies = tracker.getPocketbuddylist();
                    if(pocketbuddies == null){
                        pocketbuddies = new ArrayList<>();
                    }
                }
                PocketBuddyMoodEntity saveedPocketBuddy = pocketBuddyMoodRepo.save(pocketBuddyMoodMapper.DTOtoEntity(pocketBuddyMoodDTO));
                pocketbuddies.add(saveedPocketBuddy);
                tracker.setPocketbuddylist(pocketbuddies);
                tracker.setDate(saveedPocketBuddy.getEndDate());
                trackerRepo.save(tracker);
                //profile.setTrackerEntity(tracker);
                List<TrackerEntity> trackerEntityList = profile.getDailytrackers();
                if(trackerEntityList == null ){
                    trackerEntityList = new ArrayList<>();
                    trackerEntityList.add(tracker);
                    profile.setDailytrackers(trackerEntityList);
                }else {
                    if(!trackerEntityList.contains(tracker)){
                        trackerEntityList.add(tracker);
                        profile.setDailytrackers(trackerEntityList);
                    }
                }

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
    public TribeMoodDTO saveTribemood(TribeMoodDTO tribeMoodDTO, String identifier) {
        try {
            UserProfileEntity profile = userProfileRepo.findByidentifier(identifier);
            if(profile != null){
               // TrackerEntity tracker = profile.getTrackerEntity();
                TrackerEntity tracker = trackerRepo.findBytrackerIdentifier(tribeMoodDTO.getEndDate().toString());
                List<TribeMoodEntity> tribemoods;
                if(tracker == null){
                    TrackerEntity newtrackerEntity = new TrackerEntity();
                    newtrackerEntity.setTrackerIdentifier(tribeMoodDTO.getEndDate().toString());
                    // tracker =  trackerRepo.save(new TrackerEntity());
                    tracker =  trackerRepo.save(newtrackerEntity);
                    tribemoods = new ArrayList<>();
                }else {
                   // tribemoods = profile.getTrackerEntity().getMyTribeList();
                    tribemoods = tracker.getMyTribeList();
                    if(tribemoods == null){
                        tribemoods = new ArrayList<>();
                    }
                }
                TribeMoodEntity saveedTribemoood = tribeMoodRepo.save(tribeMoodMapper.DTOtoEntity(tribeMoodDTO));
                tribemoods.add(saveedTribemoood);
                tracker.setMyTribeList(tribemoods);
                tracker.setDate(tribeMoodDTO.getEndDate());
                trackerRepo.save(tracker);
               // profile.setTrackerEntity(tracker);
                List<TrackerEntity> trackerEntityList = profile.getDailytrackers();
                if(trackerEntityList == null ){
                    trackerEntityList = new ArrayList<>();
                    trackerEntityList.add(tracker);
                    profile.setDailytrackers(trackerEntityList);
                }else {
                    if(!trackerEntityList.contains(tracker)){
                        trackerEntityList.add(tracker);
                        profile.setDailytrackers(trackerEntityList);
                    }
                }

                // INCREAMENT THE OVERAL SMILE DURATION OF USER (Sec)
                double accumulatedValue = profile.getAccumulatedValue();
                if(accumulatedValue > 0){
                    accumulatedValue = accumulatedValue + tribeMoodDTO.getSmileduration();
                }else {
                    accumulatedValue = tribeMoodDTO.getSmileduration();
                }
                profile.setAccumulatedValue(accumulatedValue);
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
