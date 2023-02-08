package com.cowrycode.smileapp.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionnaireBMIScaleEntity extends BaseEntity {
    private int lively;
    private int happy;
    private int sad;
    private int tired;
    private int caring;
    private int content;
    private int gloomy ;
    private int jittery ;
    private int drowsy ;
    private int grouchy ;
    private int peppy ;
    private int nervous;
    private int calm;
    private int loving;
    private int fedup;
    private int active;
}
