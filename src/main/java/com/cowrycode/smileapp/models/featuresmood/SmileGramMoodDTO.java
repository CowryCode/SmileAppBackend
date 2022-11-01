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
    private LocalDate startDate; // Opened app home page
    private Time startTime; // Time Opened AppHome Page
    private int startMood; // Rating at open app Home page
    private LocalDate  endDate; // Date used this feature
    private Time endTime; // Time Used this feature
    private int endMood;  // Mood as at the time this feature was used
    private double smileduration; // Smile duration while using this feature (in seconds)
}
