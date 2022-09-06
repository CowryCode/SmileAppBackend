package com.cowrycode.smileapp.domains;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class UserProfileEntity extends BaseEntity {
    String name;
    double smilegrampoint;
}
