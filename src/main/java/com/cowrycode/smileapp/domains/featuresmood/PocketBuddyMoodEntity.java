package com.cowrycode.smileapp.domains.featuresmood;


import com.cowrycode.smileapp.domains.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class PocketBuddyMoodEntity extends BaseEntity {
    private LocalDate startDate;
    private Time startTime;
    private int startMood;
    private LocalDate  endDate;
    private Time endTime;
    private int endMood;
    private double smileduration; //in minutes
}