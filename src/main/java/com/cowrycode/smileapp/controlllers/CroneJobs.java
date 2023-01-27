package com.cowrycode.smileapp.controlllers;


import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import com.cowrycode.smileapp.services.UserProfileService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@EnableAsync
public class CroneJobs {

    final UserProfileRepo userProfileRepo;
    private final UserProfileService userProfileService;

    public CroneJobs(UserProfileRepo userProfileRepo, UserProfileService userProfileService) {
        this.userProfileRepo = userProfileRepo;
        this.userProfileService = userProfileService;
    }

    @Scheduled(cron = "0 25 * * * *")
    public void dailyReminders() {
        try{
            List<UserProfileEntity> users = userProfileRepo.findAll();
            if(LocalDateTime.now().getHour() == 8 || LocalDateTime.now().getHour() == 15  ){
                for(int x =0; x < users.size(); x++ ){
                    UserProfileEntity profileEntity = users.get(x);
                    if(profileEntity.getDeviceId() != null){
                        userProfileService.pushNotification(profileEntity.getIdentifier(), "SmileGram", "Have your smiled enough today? Play the SmileGram Game");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
