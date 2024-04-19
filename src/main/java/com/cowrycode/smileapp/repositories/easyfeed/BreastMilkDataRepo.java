package com.cowrycode.smileapp.repositories.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.BreastMilkData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreastMilkDataRepo extends JpaRepository<BreastMilkData, Long> {
    BreastMilkData findBreastMilkDataByUserID(String userID);
    List<BreastMilkData> findBreastMilkDataByUserIDAndIsbottlingOrderByDateCreatedDesc(String id, boolean type);
    List<BreastMilkData> findBreastMilkDataByUserIDAndIsbreasting(String id, boolean type);
    List<BreastMilkData> findBreastMilkDataByUserIDAndIspumping(String id, boolean type);
}
