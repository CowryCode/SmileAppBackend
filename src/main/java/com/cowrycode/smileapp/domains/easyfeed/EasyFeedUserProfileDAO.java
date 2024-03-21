package com.cowrycode.smileapp.domains.easyfeed;

import com.cowrycode.smileapp.domains.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyFeedUserProfileDAO extends BaseEntity {
    private String userID;
    private String babyName;
    private String deviceID;
    //private List<EasyFeedUserStatus> sharedUsers;
    private String listofSharedUsers; // User this to get the Status of these users
}
