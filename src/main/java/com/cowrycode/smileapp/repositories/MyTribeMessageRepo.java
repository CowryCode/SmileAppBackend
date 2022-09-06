package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyTribeMessageRepo extends JpaRepository<MyTribeMessageEntity, Long> {
}
