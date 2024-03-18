package com.cowrycode.smileapp.repositories.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.EasyFeedUserProfileDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EasyFeedUserProfilerRepo extends JpaRepository<EasyFeedUserProfileDAO, Long> {
}
