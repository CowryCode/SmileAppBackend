package com.cowrycode.smileapp.models.easyfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreastMilkDataDTO {
    private Long id;
    private int breastfeedingduration;
    private String breastSide;
    private int PumpedQuantity_ML;
    private boolean isbottling;
    private boolean ispumping;
    private boolean isbreasting;
    private String userID;
}
