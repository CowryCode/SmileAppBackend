package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import com.cowrycode.smileapp.mapper.featuresmood.PocketBuddyMoodMapper;
import com.cowrycode.smileapp.mapper.featuresmood.SmileGramMoodMapper;
import com.cowrycode.smileapp.mapper.featuresmood.TribeMoodMapper;
import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;
import com.cowrycode.smileapp.repositories.PocketBuddyMoodRepo;
import com.cowrycode.smileapp.repositories.SmileGramMoodRepo;
import com.cowrycode.smileapp.repositories.TribeMoodRepo;
import org.springframework.stereotype.Service;

@Service
public class MoodServiceImpl implements MoodService {
    private final SmileGramMoodRepo smileGramMoodRepo;
    private final PocketBuddyMoodRepo pocketBuddyMoodRepo;
    private final TribeMoodRepo tribeMoodRepo;

    private SmileGramMoodMapper smileGramMoodMapper = SmileGramMoodMapper.INSTANCE;
    private PocketBuddyMoodMapper pocketBuddyMoodMapper = PocketBuddyMoodMapper.INSTANCE;
    private TribeMoodMapper tribeMoodMapper = TribeMoodMapper.INSTANCE;

    public MoodServiceImpl(SmileGramMoodRepo smileGramMoodRepo,
                           PocketBuddyMoodRepo pocketBuddyMoodRepo,
                           TribeMoodRepo tribeMoodRepo
    ) {
        this.smileGramMoodRepo = smileGramMoodRepo;
        this.pocketBuddyMoodRepo = pocketBuddyMoodRepo;
        this.tribeMoodRepo = tribeMoodRepo;
    }

    @Override
    public SmileGramMoodDTO saveSmileGramMood(SmileGramMoodDTO smileGramMoodDTO) {
        try {
            SmileGramMoodEntity saveedSmileGram = smileGramMoodRepo.save(smileGramMoodMapper.DTOtoEntity(smileGramMoodDTO));
            return smileGramMoodMapper.EntityToDTO(saveedSmileGram);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public PocketBuddyMoodDTO savePocketBuddyMood(PocketBuddyMoodDTO pocketBuddyMoodDTO) {
        try {
            PocketBuddyMoodEntity savedPocketBuddy = pocketBuddyMoodRepo.save(pocketBuddyMoodMapper.DTOtoEntity(pocketBuddyMoodDTO));
            return pocketBuddyMoodMapper.EntityToDTO(savedPocketBuddy);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public TribeMoodDTO saveTribemood(TribeMoodDTO tribeMoodDTO) {
        try{
            TribeMoodEntity savedtribeMoodEntity = tribeMoodRepo.save(tribeMoodMapper.DTOtoEntity(tribeMoodDTO));
            return tribeMoodMapper.EntitytoDTO(savedtribeMoodEntity);
        }catch (Exception e){
            return null;
        }
    }
}
