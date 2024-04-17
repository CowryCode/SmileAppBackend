package com.cowrycode.smileapp.models.easyfeed;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String deviceID;
    //private List<EasyFeedUserStatus> sharedUsers;
    @JsonIgnore
    private String listofSharedUsers; // User this to get the Status of these users
}
