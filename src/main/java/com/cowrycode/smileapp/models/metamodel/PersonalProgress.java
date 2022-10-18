package com.cowrycode.smileapp.models.metamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalProgress {
    String day;
    int TargetValue;
    int scoredValue;
}
