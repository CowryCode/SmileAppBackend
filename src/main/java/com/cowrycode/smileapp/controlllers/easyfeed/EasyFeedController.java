package com.cowrycode.smileapp.controlllers.easyfeed;

import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.easyfeed.*;
import com.cowrycode.smileapp.services.FCMSenderService;
import com.cowrycode.smileapp.services.easyfeed.EasyFeedService;
import com.cowrycode.smileapp.services.easyfeed.utilities.FeedingRanking;
import com.cowrycode.smileapp.services.easyfeed.utilities.LeaderBoard;
import com.cowrycode.smileapp.utilities.Interview;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        System.out.println("THE USER ID IS : " + userID);
       LeaderBoard result =   easyFeedService.getLeaderBoard(userID);
        System.out.println(" Result : " + result.getUserStatus());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-height")
    public ResponseEntity<String> saveHeight(@RequestBody @Validated HeightDataDTO heightDataDTO){
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
       // fcmSenderService.sendPushnotification("CHESS","Hello Chimamaka Notification hi!!!","f7yWrhNWR92WCZrToUJodD:APA91bEt92iJfUlTbFnLu4s1B0_F0fnol8KloCoSDdvW-ZKSyZQaIBoExUwz79FEzxzEIlQA7nf4OLMTTXhVjjJGyzsLrna9mqZlgUUPEm-RQ22_38leXlX5SU2hxRcvgQUhT_DtMBCT");
       // return new ResponseEntity<>("Successfull ", HttpStatus.OK);
//        decode();
        easyFeedService.sendEmail("josephorji.laplace@gmail.com", "Test Email");
        return new ResponseEntity<>(decode(), HttpStatus.OK);
    }

    private String decode(){
        PriorityQueue<Interview> sort =
                new PriorityQueue<Interview>((a,b) ->  a.index - b.index);

        String filePath = "/Users/josephorji/downloads/interview/coding_qual_input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");
                sort.add(new Interview(Integer.parseInt(arr[0]), arr[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int counter = 1;
        int pointer = 1;
        int pointerCounter = 1;
        String result = "";
        while (!sort.isEmpty()){
            if(counter == pointer){
                Interview val =  sort.poll();
                result = result + " " + val.value;
                pointerCounter++;
                pointer = pointer + pointerCounter;
                counter++;
            }else {
                sort.poll();
                counter++;
            }
        }
        System.out.println(result);
        return  result.trim();
    }
}


