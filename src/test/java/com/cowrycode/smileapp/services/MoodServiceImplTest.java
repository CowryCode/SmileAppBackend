package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.TrackerEntity;
import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.mapper.featuresmood.PocketBuddyMoodMapper;
import com.cowrycode.smileapp.mapper.featuresmood.SmileGramMoodMapper;
import com.cowrycode.smileapp.mapper.featuresmood.TribeMoodMapper;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoodServiceImplTest {

    @Mock
    SmileGramMoodRepo smileGramMoodRepo;
    @Mock
    PocketBuddyMoodRepo pocketBuddyMoodRepo;
    @Mock
    TribeMoodRepo tribeMoodRepo;
    @Mock
    TrackerRepo trackerRepo;
    @Mock
    UserProfileRepo userProfileRepo;

    private PocketBuddyMoodMapper pocketBuddyMoodMapper = PocketBuddyMoodMapper.INSTANCE;
    private TribeMoodMapper tribeMoodMapper = TribeMoodMapper.INSTANCE;
    private SmileGramMoodMapper smileGramMoodMapper = SmileGramMoodMapper.INSTANCE;

    UserProfileEntity userProfileEntity;
    TrackerEntity trackerEntity;
    SmileGramMoodEntity smileGramMoodEntity;

    MoodService moodService;

    @BeforeEach
    void setUp() {
        moodService = new MoodServiceImpl(smileGramMoodRepo,pocketBuddyMoodRepo,tribeMoodRepo,trackerRepo,userProfileRepo);

        userProfileEntity = new UserProfileEntity();
        userProfileEntity.setId(1L);
        userProfileEntity.setName("TEST USER 1");

        trackerEntity = new TrackerEntity();
        trackerEntity.setId(1L);
        trackerEntity.setTrackerIdentifier("trackerIdentifier");
        trackerEntity.setSmilegramlist(new ArrayList<>());

        smileGramMoodEntity = new SmileGramMoodEntity();
        smileGramMoodEntity.setId(1L);
        smileGramMoodEntity.setStartDate(LocalDate.now());
        smileGramMoodEntity.setEndDate(LocalDate.now());
        smileGramMoodEntity.setStartTime(Time.valueOf(LocalTime.now()));
        smileGramMoodEntity.setEndTime(Time.valueOf(LocalTime.now()));


    }

    @AfterEach
    void tearDown() {
        userProfileEntity = null;
        trackerEntity = null;
        smileGramMoodEntity = null;
    }

    @Test
    void saveSmileGramMood() {
        //WHEN
//        when(userProfileRepo.findByidentifier(any(String.class))).thenReturn(userProfileEntity);
//        when(trackerRepo.findBytrackerIdentifier(any(String.class))).thenReturn(trackerEntity);
//        when(smileGramMoodRepo.save(any(SmileGramMoodEntity.class))).thenReturn(smileGramMoodEntity);
//
//        SmileGramMoodDTO savedDTO = moodService.saveSmileGramMood(smileGramMoodMapper.EntityToDTO(smileGramMoodEntity), "test");

//        assertNotNull(savedDTO);
//        assertEquals(savedDTO.getId(), smileGramMoodEntity.getId());
//
//        verify(userProfileRepo, times(1)).save(userProfileEntity);
//        verify(trackerRepo, times(1)).save(trackerEntity);
//        verify(smileGramMoodRepo, times(1)).save(smileGramMoodEntity);
    }

    @Test
    void savePocketBuddyMood() {
    }

    @Test
    void saveTribemood() {
    }
}