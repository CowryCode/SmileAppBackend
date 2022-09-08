package com.cowrycode.smileapp.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTribeMessageEntity extends BaseEntity {
    String content;
    int numberoflikes;
    boolean isread;
}
