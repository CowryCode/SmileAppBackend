package com.cowrycode.smileapp.domains.featuresmood;

import com.cowrycode.smileapp.domains.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmileGramMoodEntity extends BaseEntity {
    private LocalDate  startDate;
    private Time startTime;
    private int startMood;
    private LocalDate  endDate;
    private Time endTime;
    private int endMood;
    private double smileduration; //in seconds
    private int countrycount;
    private double timeSpentSec; // This is the time user spend on this feature, measured in seconds
}

