package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpathyRequestRepo extends JpaRepository<EmpathyRequestEntity, Long> , EmpathyRequestRepoCustom{

}
