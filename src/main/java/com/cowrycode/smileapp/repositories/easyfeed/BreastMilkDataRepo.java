package com.cowrycode.smileapp.repositories.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.BreastMilkData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreastMilkDataRepo extends JpaRepository<BreastMilkData, Long> {
    BreastMilkData findBreastMilkDataByUserID(String userID);
}
