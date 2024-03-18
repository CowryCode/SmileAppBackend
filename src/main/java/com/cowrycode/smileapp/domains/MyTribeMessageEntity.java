package com.cowrycode.smileapp.domains;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTribeMessageEntity extends BaseEntity {
    private String receiverID;
    private String content;
    private String sourceCountry;
    private boolean isread;
    private boolean isapproved;
}
