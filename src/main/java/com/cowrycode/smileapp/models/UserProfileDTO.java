package com.cowrycode.smileapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDTO {
    private Long id;
    String name;
    double smilegrampoint;
}
