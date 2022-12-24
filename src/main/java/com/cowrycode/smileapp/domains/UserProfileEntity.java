package com.cowrycode.smileapp.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileEntity extends BaseEntity {
    private String identifier;
    private String name;
    private String phonenumber;
    private boolean isconsented;
    private double smilegrampoint; // Number of Countries painted Green: Use Point to get Map String ("smilegrammappoints")
    private String smilegrammappoints;
    private double accumulatedValue; // Accumulated duration user has smiled (Sec.)
    private String deviceId;
    @OneToMany
    List<TrackerEntity> dailytrackers;
    private boolean voided;
}
