package com.cowrycode.smileapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTribeMessageDTO {
    private Long id;
    private Long receiverID;
    private String content;
    private String sourceCountry;
    private boolean isread;
}
