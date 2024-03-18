package com.cowrycode.smileapp.repositories.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.WeightData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightDataRepo extends JpaRepository<WeightData, Long> {
}
