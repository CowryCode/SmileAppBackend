package com.cowrycode.smileapp.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileDTO {

    private Long id;

    private String name;

    private double smilegrampoint;

    private String deviceId;
}
