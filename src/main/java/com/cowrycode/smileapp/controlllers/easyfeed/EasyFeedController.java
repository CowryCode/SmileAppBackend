package com.cowrycode.smileapp.controlllers.easyfeed;

import com.cowrycode.smileapp.models.UserProfileDTO;
import com.cowrycode.smileapp.models.easyfeed.BreastMilkDataDTO;
import com.cowrycode.smileapp.services.easyfeed.EasyFeedService;
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
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }
}
