package com.cowrycode.smileapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpathyRequestDTO {
    private Long id;
    private String senderID;
    private String receiverID;
    private String content;
    private String sourceCountry;
    @JsonIgnore
    private String respondedUsersIDs;
    private boolean responded;
}
