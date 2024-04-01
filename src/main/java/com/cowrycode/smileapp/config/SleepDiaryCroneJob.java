package com.cowrycode.smileapp.config;

import com.cowrycode.smileapp.services.FCMSenderService;
import com.cowrycode.smileapp.services.easyfeed.EasyFeedService;
import com.cowrycode.smileapp.utilities.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
@EnableAsync
public class SleepDiaryCroneJob {

    private final EasyFeedService easyFeedService;

    Logger logger = LoggerFactory.getLogger(SleepDiaryCroneJob.class);


    public SleepDiaryCroneJob(EasyFeedService easyFeedService ) {
        this.easyFeedService = easyFeedService;
    }

   // @Scheduled(cron = "0 22 * * * *")
    @Scheduled(cron = "0 15 * * * *")
    public void dailyReminders() throws InterruptedException {
        easyFeedService.sendPushnotification();
    }

}
