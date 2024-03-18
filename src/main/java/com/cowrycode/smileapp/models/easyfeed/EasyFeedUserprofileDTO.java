package com.cowrycode.smileapp.models.easyfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyFeedUserprofileDTO {
    private Long id;
    private String userID;
    private String babyName;
    //private List<EasyFeedUserStatus> sharedUsers;
    private String listofSharedUsers; // User this to get the Status of these users
}
