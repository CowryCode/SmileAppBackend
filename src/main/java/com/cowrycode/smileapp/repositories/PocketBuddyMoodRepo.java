package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketBuddyMoodRepo extends JpaRepository<PocketBuddyMoodEntity, Long> {
}
