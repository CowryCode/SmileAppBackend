package com.cowrycode.smileapp.models.metamodels;

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
    double acumulatedValue;
    double globalpercent;
}
