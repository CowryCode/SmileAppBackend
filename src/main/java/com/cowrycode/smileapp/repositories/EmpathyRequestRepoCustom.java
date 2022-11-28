package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;

import java.util.List;

public interface EmpathyRequestRepoCustom {
    List<EmpathyRequestEntity> getUnrespondedMessages(String userID);
}
