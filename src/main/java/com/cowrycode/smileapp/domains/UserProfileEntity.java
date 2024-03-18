package com.cowrycode.smileapp.domains;


import com.cowrycode.smileapp.models.UnrepliedTribeCalls;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import com.cowrycode.smileapp.models.metamodels.UnreadTribeMessagesDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    private int smilegrampoints; // Total number of Countries painted Green: Use Point to get Map String ("smilegrammappoints")
    private String smilegrammappoints;
    private double accumulatedValue; // Accumulated duration user has smiled (Sec.)
    private String deviceId;
    @OneToMany
    List<TrackerEntity> dailytrackers;

    @OneToMany
    List<QuestionnaireBMIScaleEntity> dailyquestionnaires;

    String chathistory;

    @Transient
    Long dalOpinioID;

    double accumulatedTimeSpentOnApp;

    @Transient
    double todayAccumulatedSpentTime;
    @Transient
    private boolean submittedBMI;
    @Transient
    LeaderBoard leaderBoard;
    @Transient
    UnreadTribeMessagesDTO unreadTribeMessage;
    @Transient
    UnreadTribeMessagesDTO readTribeMessages;
    @Transient
    UnrepliedTribeCalls unrepliedTribeCalls;

    @Transient
    private int todayTargetValue;
    @Transient
    private int todayAchievedValue;

    private boolean voided;
}
