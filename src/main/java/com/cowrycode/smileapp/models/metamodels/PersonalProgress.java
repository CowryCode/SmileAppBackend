package com.cowrycode.smileapp.models.metamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalProgress{
    int id;
    int TargetValue;
    int scoredValue;
    String identifier;
}
