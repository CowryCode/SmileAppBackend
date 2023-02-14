package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;
import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.mapper.MyTribeMessageMapper;
import com.cowrycode.smileapp.models.MyTribeMessageDTO;
import com.cowrycode.smileapp.repositories.MyTribeMessageRepo;
import com.cowrycode.smileapp.repositories.UserProfileRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyTribeMessageServiceImpl implements MyTribeMessageService {
    private final MyTribeMessageRepo myTribeMessageRepo;
    private final UserProfileRepo userProfileRepo;

    private final FCMSenderService fcmSenderService;

    private MyTribeMessageMapper myTribeMessageMapper = MyTribeMessageMapper.INSTANCE;

//    public MyTribeMessageServiceImpl(MyTribeMessageRepo myTribeMessageRepo, UserProfileRepo userProfileRepo) {
//        this.myTribeMessageRepo = myTribeMessageRepo;
//        this.userProfileRepo = userProfileRepo;
//    }


    public MyTribeMessageServiceImpl(MyTribeMessageRepo myTribeMessageRepo, UserProfileRepo userProfileRepo, FCMSenderService fcmSenderService) {
        this.myTribeMessageRepo = myTribeMessageRepo;
        this.userProfileRepo = userProfileRepo;
        this.fcmSenderService = fcmSenderService;
    }

    @Override
    public MyTribeMessageDTO saveTribeMessage(MyTribeMessageDTO myTribeMessageDTO, String userIdentifer) {
        try{
            MyTribeMessageEntity savedMessage = myTribeMessageRepo.save(myTribeMessageMapper.DTOtoEntity(myTribeMessageDTO));
            return myTribeMessageMapper.EntityToDTO(savedMessage);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MyTribeMessageDTO> getTribeMessage(String userIdentifier,  boolean isread) {
        try {
           // List<MyTribeMessageEntity> messages = myTribeMessageRepo.getUnreadSmilePacks(userIdentifier,isread);
            UserProfileEntity userProfileEntity = userProfileRepo.findByIdentifierOrName(userIdentifier, userIdentifier);
            if(userProfileEntity == null) return null;
            List<MyTribeMessageEntity> messages = myTribeMessageRepo.findMyTribeMessageEntitiesByReceiverIDOrReceiverIDAndIsapprovedTrue(userProfileEntity.getIdentifier(), userProfileEntity.getName());
            return  messages.stream().map(myTribeMessageMapper::EntityToDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MyTribeMessageDTO> getUnapprovedTribeMessage() {
        try {
            List<MyTribeMessageEntity> messages = myTribeMessageRepo.findMyTribeMessageEntitiesByIsapprovedFalseAndIsreadFalse();
            return  messages.stream().map(myTribeMessageMapper::EntityToDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MyTribeMessageDTO> approveTribeMessage(Long messageID, String recieverID) {
        try {
            MyTribeMessageEntity message = myTribeMessageRepo.findById(messageID).orElse(null);
            if(message == null ) return null;
            message.setIsapproved(true);
            myTribeMessageRepo.save(message);
            notifyUser(recieverID);
            return  getUnapprovedTribeMessage() ;
        }catch (Exception e){
            return null;
        }
    }

    private void notifyUser(String userID) {
        try {
            UserProfileEntity userProfileEntity = userProfileRepo.findByIdentifierOrName(userID, userID);
            if (userProfileEntity != null && userProfileEntity.getDeviceId() != null)
                fcmSenderService.sendPushnotification("I Care", "I sent you an empathic note, check the SmileApp", userProfileEntity.getDeviceId());
        } catch (Exception e) {

        }
    }

    @Override
    public List<MyTribeMessageDTO>  deleteTribeMessage(Long messageID) {
        try {
            myTribeMessageRepo.deleteById(messageID);
            return  getUnapprovedTribeMessage();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MyTribeMessageDTO> readTribeMessage(MyTribeMessageDTO myTribeMessageDTO, String userIdentifier) {
        try{
            MyTribeMessageEntity myTribeMessageEntity = myTribeMessageRepo.getReferenceById(myTribeMessageDTO.getId());
            if(myTribeMessageEntity != null){
                myTribeMessageEntity.setIsread(myTribeMessageDTO.isIsread());
                myTribeMessageRepo.save(myTribeMessageEntity);
                return getTribeMessage(userIdentifier, false);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
