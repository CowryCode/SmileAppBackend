package com.cowrycode.smileapp.models.metamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GlobalProgress {
    String username;
    int acumulatedValue;
    double globalpercent;
}
