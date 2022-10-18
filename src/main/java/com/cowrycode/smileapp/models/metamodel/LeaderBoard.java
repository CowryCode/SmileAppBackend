package com.cowrycode.smileapp.models.metamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaderBoard {
    List<PersonalProgress> personalProgresses;
    List<GlobalProgress> globalProgresses;
}
