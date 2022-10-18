package com.cowrycode.smileapp.models;

import com.cowrycode.smileapp.domains.TrackerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    private Long identifier;
    private String name;
    private String phonenumber;
    private boolean isconsented;
    private double smilegrampoint;
    private String deviceId;
//    private TrackerEntity trackerEntity;
    List<TrackerEntity> dailytrackers;
}
