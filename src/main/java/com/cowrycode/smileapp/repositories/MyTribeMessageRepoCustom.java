package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;

import java.util.List;

public interface MyTribeMessageRepoCustom {
    List<MyTribeMessageEntity> getUnreadSmilePacks(String userID, String userName, boolean isread);
}
