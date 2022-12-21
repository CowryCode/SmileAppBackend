package com.cowrycode.smileapp.controlllers;


import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import com.cowrycode.smileapp.services.FCMSenderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@EnableAsync
public class CroneJobs {

    final UserProfileRepo userProfileRepo;
    final FCMSenderService fcmSenderService;

    public CroneJobs(UserProfileRepo userProfileRepo, FCMSenderService fcmSenderService) {
        this.userProfileRepo = userProfileRepo;
        this.fcmSenderService = fcmSenderService;
    }

    @Scheduled(cron = "0 10 * * * *")
    public void dailyReminders() {
        try{
            List<UserProfileEntity> users = userProfileRepo.findAll();


            System.out.println("CRONE JOB RUN !");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
