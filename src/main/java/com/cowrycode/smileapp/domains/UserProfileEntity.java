package com.cowrycode.smileapp.domains;


import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileEntity extends BaseEntity {
    String name;
    double smilegrampoint;

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
