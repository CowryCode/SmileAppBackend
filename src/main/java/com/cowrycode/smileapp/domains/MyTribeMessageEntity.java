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
    private String content;
    private int numberoflikes;
    private boolean isread;
}
