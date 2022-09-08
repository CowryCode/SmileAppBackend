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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSmilegrampoint() {
        return smilegrampoint;
    }

    public void setSmilegrampoint(double smilegrampoint) {
        this.smilegrampoint = smilegrampoint;
    }
}
