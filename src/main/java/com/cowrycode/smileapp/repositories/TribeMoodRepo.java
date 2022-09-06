package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TribeMoodRepo extends JpaRepository<TribeMoodEntity, Long> {
}
