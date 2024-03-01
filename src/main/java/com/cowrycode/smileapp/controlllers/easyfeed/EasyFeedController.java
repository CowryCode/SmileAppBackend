package com.cowrycode.smileapp.controlllers.easyfeed;

import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.easyfeed.BreastMilkDataDTO;
import com.cowrycode.smileapp.services.easyfeed.EasyFeedService;
import com.cowrycode.smileapp.services.easyfeed.utilities.LeaderBoard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/easyfeed")
public class EasyFeedController {

    private final EasyFeedService easyFeedService;

    public EasyFeedController(EasyFeedService easyFeedService) {
        this.easyFeedService = easyFeedService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save-breast-milk")
    public ResponseEntity<String> createUser(@RequestBody @Validated BreastMilkDataDTO breastMilkDataDTO){
        easyFeedService.saveMilkData(breastMilkDataDTO);
       // maxVowels("abciiidef", 3);
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-leaderboard/{userID}")
    public ResponseEntity<LeaderBoard> getLeaderBoard(@PathVariable("userID") String userID){
       LeaderBoard result =   easyFeedService.getLeaderBoard(userID);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    private int maxVowels(String s, int k) {
        char[] arr = s.toCharArray();
        int maxCount = 0;
        int count = 0;
        boolean onoff = false;

        for (int z = 0; z < arr.length; z++) {
            if (arr[z] == 'a' | arr[z] == 'e' | arr[z] == 'i' | arr[z] == 'o' | arr[z] == 'u') {
                System.out.println(" char : " + arr[z]);
                if (!onoff) {
                    onoff = true;
                }
                count++;
            } else {
                if(onoff){
                    if(count >= maxCount){
                        maxCount = count;
                        onoff = false;
                        count = 0;
                        System.out.println("************ Max Value : " + maxCount);
                    }
                }
            }
        }
        System.out.println("Out put : " + maxCount);
        return maxCount;
    }
}
