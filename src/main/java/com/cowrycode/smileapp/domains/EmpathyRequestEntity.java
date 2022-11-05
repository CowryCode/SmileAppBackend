package com.cowrycode.smileapp.domains;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrePersist;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpathyRequestEntity extends BaseEntity {
    private Long senderID;
    private Long receiverID;
    private String content;
    private String sourceCountry;
    private String respondedUsersIDs;
    private boolean responded;


    @PrePersist
    public void prepersist(){
        if(respondedUsersIDs == null){
            respondedUsersIDs = "Responded User:";
        }
    }
}
