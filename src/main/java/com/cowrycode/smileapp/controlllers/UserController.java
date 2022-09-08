package com.cowrycode.smileapp.controlllers;


import com.cowrycode.smileapp.models.MyTribeMessageDTO;
import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;
import com.cowrycode.smileapp.services.MoodService;
import com.cowrycode.smileapp.services.MyTribeMessageService;
import com.cowrycode.smileapp.services.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/smile")
public class UserController {

    private final MoodService moodService;
    private final MyTribeMessageService myTribeMessageService;
    private final UserProfileService userProfileService;


    public UserController(MoodService moodService, MyTribeMessageService myTribeMessageService, UserProfileService userProfileService) {
        this.moodService = moodService;
        this.myTribeMessageService = myTribeMessageService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserProfileDTO> createUser(@RequestBody @Validated UserProfileDTO userProfileDTO , HttpServletRequest request){
        UserProfileDTO savedProfile = userProfileService.saveUserprofile(userProfileDTO);
        return new ResponseEntity<>(savedProfile, HttpStatus.OK);
    }
    @PostMapping("/tribemessage")
    public ResponseEntity<MyTribeMessageDTO> saveTribeMessage(@RequestBody @Validated MyTribeMessageDTO myTribeMessageDTO, HttpServletRequest request){
        MyTribeMessageDTO savedMessage = myTribeMessageService.saveTribeMessage(myTribeMessageDTO);
        return new ResponseEntity<>(savedMessage, HttpStatus.OK);
    }

    @PostMapping("/smilegram-mood")
    public ResponseEntity<SmileGramMoodDTO> saveSmilegramMood(@RequestBody @Validated SmileGramMoodDTO smileGramMoodDTO, HttpServletRequest request){
        SmileGramMoodDTO savedsmilegram = moodService.saveSmileGramMood(smileGramMoodDTO);
        return new ResponseEntity<>(savedsmilegram, HttpStatus.OK);
    }

    @PostMapping("/pocketbuddy-mood")
    public ResponseEntity<PocketBuddyMoodDTO> savePocketbuddyMood(@RequestBody @Validated PocketBuddyMoodDTO pocketBuddyMoodDTO, HttpServletRequest request){
        PocketBuddyMoodDTO savedpocketbuddy = moodService.savePocketBuddyMood(pocketBuddyMoodDTO);
        return new ResponseEntity<>(savedpocketbuddy, HttpStatus.OK);
    }

    @PostMapping("/tribe-mood")
    public ResponseEntity<TribeMoodDTO> savePocketbuddyMood(@RequestBody @Validated TribeMoodDTO tribeMoodDTO, HttpServletRequest request){
        TribeMoodDTO savedtribemood = moodService.saveTribemood(tribeMoodDTO);
        return new ResponseEntity<>(savedtribemood, HttpStatus.OK);
    }
}
