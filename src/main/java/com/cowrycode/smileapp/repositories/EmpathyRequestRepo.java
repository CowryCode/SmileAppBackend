package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;
import com.cowrycode.smileapp.domains.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpathyRequestRepo extends JpaRepository<EmpathyRequestEntity, Long> , EmpathyRequestRepoCustom{
    List<EmpathyRequestEntity> findEmpathyRequestEntitiesBySenderIDIsNotIgnoreCaseAndSenderIDIsNotIgnoreCase(String identifier, String name);
}
