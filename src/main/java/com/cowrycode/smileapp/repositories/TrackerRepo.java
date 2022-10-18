package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.TrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerRepo extends JpaRepository<TrackerEntity, Long> {
}
