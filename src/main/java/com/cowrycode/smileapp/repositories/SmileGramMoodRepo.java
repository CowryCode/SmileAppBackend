package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmileGramMoodRepo extends JpaRepository<SmileGramMoodEntity, Long> {
}
