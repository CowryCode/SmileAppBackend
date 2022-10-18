package com.cowrycode.smileapp.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileEntity extends BaseEntity {
    private Long identifier;
    private String name;
    private String phonenumber;
    private boolean isconsented;
    private double smilegrampoint;
    private String deviceId;

    @OneToOne(cascade = CascadeType.ALL)
    private TrackerEntity trackerEntity;
}
