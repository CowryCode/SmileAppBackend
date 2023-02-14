package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.MyTribeMessageDTO;

import java.util.List;

public interface MyTribeMessageService {
    MyTribeMessageDTO saveTribeMessage(MyTribeMessageDTO myTribeMessageDTO, String userIdentifier);
    List<MyTribeMessageDTO> getTribeMessage(String userIdentifier,  boolean isread);
    List<MyTribeMessageDTO> readTribeMessage(MyTribeMessageDTO myTribeMessageDTO, String userIdentifier);
    List<MyTribeMessageDTO> getUnapprovedTribeMessage();

    List<MyTribeMessageDTO> approveTribeMessage(Long messageID, String recieverID);

    List<MyTribeMessageDTO>  deleteTribeMessage(Long messageID);
}
