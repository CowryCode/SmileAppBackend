package com.cowrycode.smileapp.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class MyTribeMessageEntity extends BaseEntity {
    String content;
    int numberoflikes;
    boolean isread;
}
