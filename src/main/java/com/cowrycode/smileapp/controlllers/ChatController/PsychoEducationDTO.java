package com.cowrycode.smileapp.controlllers.ChatController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychoEducationDTO {
    private Long id;
    private boolean morethan30MinstoSleep;
    private boolean wakeupfrequentlyatnight;
    private boolean wakeuptooearly;
    private boolean sleepqualitypoor;
    private boolean ifeelconfident;
    private boolean ithinkitsdifficult;
    private boolean idontknow;
    private boolean completed;
}
