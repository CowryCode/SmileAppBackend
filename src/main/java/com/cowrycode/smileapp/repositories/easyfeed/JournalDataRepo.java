package com.cowrycode.smileapp.repositories.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.JournalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalDataRepo extends JpaRepository<JournalData, Long> {
}
