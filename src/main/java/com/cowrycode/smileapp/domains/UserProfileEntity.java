package com.cowrycode.smileapp.domains;


import com.cowrycode.smileapp.models.UnrepliedTribeCalls;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import com.cowrycode.smileapp.models.metamodels.UnreadTribeMessagesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileEntity extends BaseEntity {
    private String identifier;
    private String name;
    private String phonenumber;
    private boolean isconsented;
    private double smilegrampoint; // Number of Countries painted Green: Use Point to get Map String ("smilegrammappoints")
    private String smilegrammappoints;
    private double accumulatedValue; // Accumulated duration user has smiled (Sec.)
    private String deviceId;
    @OneToMany
    List<TrackerEntity> dailytrackers;
    @OneToMany
    List<QuestionnaireBMIScaleEntity> dailyquestionnaires;
    String chathistory;

    @Transient
    LeaderBoard leaderBoard;
    @Transient
    UnreadTribeMessagesDTO unreadTribeMessage;
    @Transient
    UnreadTribeMessagesDTO readTribeMessages;
    @Transient
    UnrepliedTribeCalls unrepliedTribeCalls;

    private boolean voided;
}
