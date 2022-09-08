package com.cowrycode.smileapp.models.featuresmood;


import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SmileGramMoodDTO {
    private Long id;
    private LocalDate startDate;
    private Time startTime;
    private int startMood;
    private LocalDate  endDate;
    private Time endTime;
    private int endMood;
    private double smileduration; //in minutes
}
