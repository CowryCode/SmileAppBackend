package com.cowrycode.smileapp.domains;


import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpathyRequestEntity extends BaseEntity {
    private String senderID;
    @Transient
    private String receiverID;
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
