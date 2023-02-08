package com.cowrycode.smileapp.models;

import com.cowrycode.smileapp.domains.QuestionnaireBMIScaleEntity;
import com.cowrycode.smileapp.domains.TrackerEntity;
import com.cowrycode.smileapp.models.metamodels.LeaderBoard;
import com.cowrycode.smileapp.models.metamodels.UnreadTribeMessagesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    //private Long identifier;
    private String identifier;
    private String name;
    private String phonenumber;
    private boolean isconsented;
    private int smilegrampoints;
    private String smilegrammappoints;
    private double accumulatedValue; // Total Number of country user have painted green

    private int todayTargetValue;
    private int todayAchievedValue;
    double todayAccumulatedSpentTime;

    @JsonIgnore
    private String deviceId;
    @JsonIgnore
    List<TrackerEntity> dailytrackers;

    @JsonIgnore
    List<QuestionnaireBMIScaleEntity> dailyquestionnaires;
    String chathistory;

    double accumulatedTimeSpentOnApp;

    private boolean submittedBMI;

    LeaderBoard leaderBoard;
    UnreadTribeMessagesDTO unreadTribeMessage;
    UnreadTribeMessagesDTO readTribeMessages;
    UnrepliedTribeCalls unrepliedTribeCalls;


    private boolean voided;
}
