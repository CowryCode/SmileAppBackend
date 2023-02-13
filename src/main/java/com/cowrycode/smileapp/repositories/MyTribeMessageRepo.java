package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyTribeMessageRepo extends JpaRepository<MyTribeMessageEntity, Long>, MyTribeMessageRepoCustom {
    List<MyTribeMessageEntity> findMyTribeMessageEntitiesByReceiverIDOrReceiverIDAndIsreadFalse(String userIdentifier,String name);

    List<MyTribeMessageEntity> findMyTribeMessageEntitiesByIsapprovedFalseAndIsreadFalse();
}
