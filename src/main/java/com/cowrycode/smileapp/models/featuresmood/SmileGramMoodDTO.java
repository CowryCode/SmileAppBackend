package com.cowrycode.smileapp.models.featuresmood;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SmileGramMoodDTO {
    private Long id;
    LocalDate startDate;
    Time startTime;
    int startMood;
    LocalDate  endDate;
    Time endTime;
    int endMood;
    double smileduration; //in minutes
}
