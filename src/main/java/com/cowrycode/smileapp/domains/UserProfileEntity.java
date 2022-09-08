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

}
