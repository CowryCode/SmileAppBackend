package com.cowrycode.smileapp.models;

import com.cowrycode.smileapp.domains.TrackerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    //private Long identifier;
    private String identifier;
    private String name;
    private String phonenumber;
    private boolean isconsented;
    private double smilegrampoints;
    private String smilegrammappoints;
    private double accumulatedValue; // Total Number of country user have painted green
    @JsonIgnore
    private String deviceId;
    @JsonIgnore
    List<TrackerEntity> dailytrackers;
    private boolean voided;
}
