package com.cowrycode.smileapp.models.metamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalProgress implements Comparable<PersonalProgress>{
    int id;
    int TargetValue;
    int scoredValue;

    @Override
    public int compareTo(PersonalProgress o) {
        int compareID = o.getId();
        return this.id - compareID;
    }
}
