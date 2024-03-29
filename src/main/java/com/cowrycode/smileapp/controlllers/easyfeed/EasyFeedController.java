package com.cowrycode.smileapp.controlllers.easyfeed;

import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.easyfeed.*;
import com.cowrycode.smileapp.services.FCMSenderService;
import com.cowrycode.smileapp.services.easyfeed.EasyFeedService;
import com.cowrycode.smileapp.services.easyfeed.utilities.LeaderBoard;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/easyfeed")
public class EasyFeedController {

    private final EasyFeedService easyFeedService;
    private final FCMSenderService fcmSenderService;
    public EasyFeedController(EasyFeedService easyFeedService, FCMSenderService fcmSenderService) {
        this.easyFeedService = easyFeedService;
        this.fcmSenderService = fcmSenderService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-breast-milk")
    public ResponseEntity<String> saveBreastMilk(@RequestBody @Validated BreastMilkDataDTO breastMilkDataDTO){
        easyFeedService.saveMilkData(breastMilkDataDTO);
       // maxVowels("abciiidef", 3);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-leaderboard/{userID}")
    public ResponseEntity<LeaderBoard> getLeaderBoard(@PathVariable("userID") String userID){
     //  LeaderBoard result =   easyFeedService.getLeaderBoard(Long.valueOf(userID));
       LeaderBoard result =   easyFeedService.getLeaderBoard(userID);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-height")
    public ResponseEntity<String> saveHeight(@RequestBody @Validated HeightDataDTO heightDataDTO){
        System.out.println(" HEIGHT IS : " + heightDataDTO.getHeight());
        easyFeedService.saveHeightData(heightDataDTO);
        // maxVowels("abciiidef", 3);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-weight")
    public ResponseEntity<String> saveWeight(@RequestBody @Validated WeightDataDTO weightDataDTO){
        System.out.println(" WEIGHT IS : " + weightDataDTO.getWeight());
        easyFeedService.saveWeigthtData(weightDataDTO);
        // maxVowels("abciiidef", 3);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-feedback")
    public ResponseEntity<String> saveFeedback(@RequestBody @Validated FeedBackDTO feedBackDTO){
        easyFeedService.saveFeedBack(feedBackDTO);
        // maxVowels("abciiidef", 3);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-userprofile")
    public ResponseEntity<String> saveEasyFeedUserProfile(@RequestBody @Validated EasyFeedUserprofileDTO easyFeedUserprofileDTO){
        easyFeedService.saveEasyFeedUserProfile(easyFeedUserprofileDTO);
        // maxVowels("abciiidef", 3);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/get-userprofile")
    public ResponseEntity<EasyFeedUserprofileDTO> getEasyFeedUserProfile(@RequestBody @Validated EasyFeedUserprofileDTO easyFeedUserprofileDTO){
        EasyFeedUserprofileDTO profile = easyFeedService.getEasyFeedUserProfile(easyFeedUserprofileDTO.getUserID());
        // maxVowels("abciiidef", 3);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-journal")
    public ResponseEntity<String> saveJournalData(@RequestBody @Validated JournalDataDTO journalDataDTO){
        easyFeedService.saveJournalData(journalDataDTO);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-deviceId")
    public ResponseEntity<String> saveDeviceID(@RequestBody @Validated EasyFeedUserprofileDTO easyFeedUserprofileDTO){
        easyFeedService.saveDeviceID(easyFeedUserprofileDTO.getUserID(), easyFeedUserprofileDTO.getDeviceID());
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/push-notification")
    public ResponseEntity<String> getnotify(){
        fcmSenderService.sendPushnotification("CHESS","Hello Chimamaka Notification hi!!!","f7yWrhNWR92WCZrToUJodD:APA91bEt92iJfUlTbFnLu4s1B0_F0fnol8KloCoSDdvW-ZKSyZQaIBoExUwz79FEzxzEIlQA7nf4OLMTTXhVjjJGyzsLrna9mqZlgUUPEm-RQ22_38leXlX5SU2hxRcvgQUhT_DtMBCT");
        return new ResponseEntity<>("Successfull ", HttpStatus.OK);
    }
}
