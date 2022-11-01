package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;
import com.cowrycode.smileapp.mapper.MyTribeMessageMapper;
import com.cowrycode.smileapp.models.MyTribeMessageDTO;
import com.cowrycode.smileapp.repositories.MyTribeMessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyTribeMessageServiceImpl implements MyTribeMessageService {
    private final MyTribeMessageRepo myTribeMessageRepo;

    private MyTribeMessageMapper myTribeMessageMapper = MyTribeMessageMapper.INSTANCE;


    public MyTribeMessageServiceImpl(MyTribeMessageRepo myTribeMessageRepo) {
        this.myTribeMessageRepo = myTribeMessageRepo;
    }

    @Override
    public MyTribeMessageDTO saveTribeMessage(MyTribeMessageDTO myTribeMessageDTO, Long userIdentifer) {
        try{
            MyTribeMessageEntity savedMessage = myTribeMessageRepo.save(myTribeMessageMapper.DTOtoEntity(myTribeMessageDTO));
            return myTribeMessageMapper.EntityToDTO(savedMessage);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MyTribeMessageDTO> getTribeMessage(Long userIdentifier) {
        try {
            List<MyTribeMessageEntity> messages = myTribeMessageRepo.getUnreadSmilePacks(userIdentifier);
            return  messages.stream().map(myTribeMessageMapper::EntityToDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            return null;
        }

    }
}
