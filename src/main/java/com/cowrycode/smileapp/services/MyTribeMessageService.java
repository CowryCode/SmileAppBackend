package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.models.MyTribeMessageDTO;

import java.util.List;

public interface MyTribeMessageService {
    MyTribeMessageDTO saveTribeMessage(MyTribeMessageDTO myTribeMessageDTO, Long userIdentifier);
    List<MyTribeMessageDTO> getTribeMessage(Long userIdentifier);
}
