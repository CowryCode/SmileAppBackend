package com.cowrycode.smileapp.repositories.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepo extends JpaRepository<FeedBack, Long> {
}
