package com.cowrycode.smileapp.services.easyfeed;

import com.cowrycode.smileapp.config.CustomException;
import com.cowrycode.smileapp.domains.easyfeed.BreastMilkData;
import com.cowrycode.smileapp.mapper.easyfeed.BreastMilkDataMapper;
import com.cowrycode.smileapp.models.easyfeed.BreastMilkDataDTO;
import com.cowrycode.smileapp.repositories.easyfeed.BreastMilkDataRepo;
import com.cowrycode.smileapp.services.easyfeed.utilities.EasyFeedStatus;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EasyFeedServiceImpl implements EasyFeedService{
    private final BreastMilkDataRepo breastMilkDataRepo;
    private final BreastMilkDataMapper mapper = BreastMilkDataMapper.INSTANCE;

    private List<BreastMilkData> breastList;
    private List<BreastMilkData> bottleList;
    private List<BreastMilkData> pumpList;
    // String == UserID
    // Interger == count per day
//    private Map<String, Map<LocalDate, Integer>> mappedDataBreast;
//    private Map<String, Map<LocalDate, Integer>> mappedDataBottle;
//    private Map<String, Map<LocalDate, Integer>> mappedDataPump;
    // DATE , USER ID , USAGE COUNT
    private Map<LocalDate, Map<String, Integer>> mappedDataBreast;
    private Map<LocalDate, Map<String, Integer>> mappedDataBottle;
    private Map<LocalDate, Map<String, Integer>> mappedDataPump;

    private Map<LocalDate, Integer> currentUserBreastData;
    private Map<LocalDate, Integer> currentUserPumpData;
    private Map<LocalDate, Integer> currentUserBottleData;

    public EasyFeedServiceImpl(BreastMilkDataRepo breastMilkDataRepo) {
        this.breastMilkDataRepo = breastMilkDataRepo;
        breastList = new ArrayList<>();
        bottleList = new ArrayList<>();
        pumpList = new ArrayList<>();
        mappedDataBreast = new HashMap<>();
        mappedDataBottle = new HashMap<>();
        mappedDataPump = new HashMap<>();
        currentUserBreastData = new HashMap<>();
        currentUserPumpData = new HashMap<>();
        currentUserBottleData = new HashMap<>();
    }

    @Override
    public void saveMilkData(BreastMilkDataDTO breastMilkDataDTO) {
      // BreastMilkData savedData = breastMilkDataRepo.save(mapper.DTOtoEntity(breastMilkDataDTO));

        getStatus("admin1@gmail.com");

//       if(savedData == null){
//           throw new CustomException("Failed to save breast feeding data in EasyFeedService");
//       }

    }

    @Override
    public EasyFeedStatus getStatus(String userID) {
        try {
        List<BreastMilkData> bdata = breastMilkDataRepo.findAll();

       // bdata.stream().forEach(this::separateDate);

        bdata.stream().forEach((breastMilkData)->{
            this.separateDate(breastMilkData, userID);
        });

        System.out.println("BREAST FEEDING COUNT : " + breastList.size());
        System.out.println("BREAST PUMPING COUNT : " + pumpList.size());
        System.out.println("BREAST BOTTLING COUNT : " + bottleList.size());

        organizeDataByUser(userID);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void organizeDataByUser(String userID){
        try{
            int bfeedingCount = 0;
            int bottlingCount = 0;
            int pumpingCount = 0;

            System.out.println("Total DAYS in Breast Feeding  " + mappedDataBreast.size() );
            for (Map.Entry<LocalDate, Map<String, Integer>> entry : mappedDataBreast.entrySet()) {
                System.out.println("Date: " + entry.getKey() + ", Total Number of Users: " + entry.getValue().size());
                for (Map.Entry<String, Integer> ent : entry.getValue().entrySet()) {
                    System.out.println("User ID : " + ent.getKey() + ", Breast Feeding count " + ent.getValue());
                }
            }
            System.out.println("******************************************");
            System.out.println("Total DAYS in Pumping " + mappedDataPump.size());
            for (Map.Entry<LocalDate, Map<String, Integer>> entry : mappedDataPump.entrySet()) {
                System.out.println("Date: " + entry.getKey() + ", Total Number of Users: " + entry.getValue().size());
                for (Map.Entry<String, Integer> ent : entry.getValue().entrySet()) {
                    System.out.println("User ID : " + ent.getKey() + ", Pumping count " + ent.getValue());
                }
            }
            System.out.println("******************************************");
            System.out.println("Total DAYS in  bottling " + mappedDataBottle.size());
            for (Map.Entry<LocalDate, Map<String, Integer>> entry : mappedDataBottle.entrySet()) {
                System.out.println("Date: " + entry.getKey() + ", Total Number of Users: " + entry.getValue().size());
                for (Map.Entry<String, Integer> ent : entry.getValue().entrySet()) {
                    System.out.println("User ID : " + ent.getKey() + ", Bottling count " + ent.getValue());
                }
            }
            System.out.println("******************************************");

            System.out.println(":::::::::::::::::: USER DATA FOR " + userID + " :::::::::::::::");
            for (Map.Entry<LocalDate, Integer>  ent : currentUserBreastData.entrySet()) {
                System.out.println("Date : " + ent.getKey() + ", Breast count " + ent.getValue());
            }
            for (Map.Entry<LocalDate, Integer>  ent : currentUserBottleData.entrySet()) {
                System.out.println("Date : " + ent.getKey() + ", Bottle count " + ent.getValue());
            }
            for (Map.Entry<LocalDate, Integer>  ent : currentUserPumpData.entrySet()) {
                System.out.println("Date : " + ent.getKey() + ", Pump count " + ent.getValue());
            }

        }catch (Exception e){
            throw new CustomException("Failed at organizing data by user");
        }
    }

    private void populateCurrentUserData(LocalDate date, int datatype){
        if(datatype == 0){
            if(currentUserBreastData.containsKey(date)){
                Integer newVal = currentUserBreastData.get(date) + 1;
                currentUserBreastData.put(date, newVal);
            }else {
                currentUserBreastData.put(date, 1);
            }
        }else if(datatype == 1){
            if(currentUserBottleData.containsKey(date)){
                Integer newVal = currentUserBottleData.get(date) + 1;
                currentUserBottleData.put(date, newVal);
            }else {
                currentUserBottleData.put(date, 1);
            }
        }else if(datatype == 2){
            if(currentUserPumpData.containsKey(date)){
                Integer newVal = currentUserPumpData.get(date) + 1;
                currentUserPumpData.put(date, newVal);
            }else {
                currentUserPumpData.put(date, 1);
            }
        }

    }

    private void separateDate(BreastMilkData breastMilkData, String currenUser){
        //String id =  breastMilkData.getUserID().trim();
        String userId =  breastMilkData.getUserID().trim();
        LocalDate datecreated =  breastMilkData.getDateCreated().toLocalDate();
        System.out.println("User ID : " + breastMilkData.getUserID());
        if(breastMilkData.isIsbreasting()) {
            breastList.add(breastMilkData);
            if(userId.equals(currenUser.trim())) populateCurrentUserData(breastMilkData.getDateCreated().toLocalDate(), 0);
           // if(mappedDataBreast.containsKey(id)){
            if(mappedDataBreast.containsKey(datecreated)){
                System.out.println("Old BreastFeed : " + breastMilkData.isIsbreasting());
               // Map<LocalDate, Integer> dayData = mappedDataBreast.get(id);
                Map<String, Integer> dayData = mappedDataBreast.get(datecreated);
                //LocalDate date = breastMilkData.getDateCreated().toLocalDate();
                if(dayData.containsKey(userId)){
                    Integer count = dayData.get(userId) + 1;
                    dayData.put(userId, count);
                }else {
                    dayData.put(userId, 1);
                }
                mappedDataBreast.put(datecreated, dayData);
                System.out.println(" Added size " + mappedDataBreast.size());
            }else {
                System.out.println("New BreastFeed : " + breastMilkData.isIsbreasting());
                Map<String, Integer> dayData = new HashMap<>();
                // LocalDate date = breastMilkData.getDateCreated().toLocalDate();
                dayData.put(userId, 1);
                mappedDataBreast.put(datecreated, dayData);
            }
        }

        if(breastMilkData.isIsbottling()){
            pumpList.add(breastMilkData);
            if(userId.equals(currenUser.trim())) populateCurrentUserData(breastMilkData.getDateCreated().toLocalDate(),1);
            if(mappedDataBottle.containsKey(datecreated)){
                System.out.println("Old Pumping : " + breastMilkData.isIspumping() );
                // Map<LocalDate, Integer> dayData = mappedDataBottle.get(id);
                Map<String, Integer> dayData = mappedDataBottle.get(datecreated);
                // LocalDate date = breastMilkData.getDateCreated().toLocalDate();
                if(dayData.containsKey(userId)){
                    Integer count = dayData.get(userId) + 1;
                    dayData.put(userId, count);
                }else {
                    dayData.put(userId, 1);
                }
                mappedDataBottle.put(datecreated, dayData);
                System.out.println(" Added size " + mappedDataBottle.size());
            }else {
                System.out.println("New Pumping : " + breastMilkData.isIspumping() );
                // Map<LocalDate, Integer> dayData = new HashMap<>();
                Map<String, Integer>  dayData = new HashMap<>();
                //LocalDate date = breastMilkData.getDateCreated().toLocalDate();
                dayData.put(userId, 1);
                mappedDataBottle.put(datecreated, dayData);
            }
        }

        if(breastMilkData.isIspumping()){
            bottleList.add(breastMilkData);
            if(userId.equals(currenUser.trim())) populateCurrentUserData(breastMilkData.getDateCreated().toLocalDate(), 2);
            if(mappedDataPump.containsKey(datecreated)){
                System.out.println("Old Bottling : " + breastMilkData.isIsbottling());
                Map<String, Integer>  dayData = mappedDataPump.get(datecreated);
                // LocalDate date = breastMilkData.getDateCreated().toLocalDate();
                if(dayData.containsKey(userId)){
                    Integer count = dayData.get(userId) + 1;
                    dayData.put(userId, count);
                }else {
                    dayData.put(userId, 1);
                }
                mappedDataPump.put(datecreated, dayData);
                System.out.println(" Added size " + mappedDataPump.size());
            }else {
                System.out.println("New Bottling : " + breastMilkData.isIsbottling());
                Map<String, Integer> dayData = new HashMap<>();
                //LocalDate date = breastMilkData.getDateCreated().toLocalDate();
                dayData.put(userId, 1);
                mappedDataPump.put(datecreated, dayData);
            }
        }
        System.out.println("***********************************************************");
    }
}
