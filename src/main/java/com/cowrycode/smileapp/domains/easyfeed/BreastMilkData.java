package com.cowrycode.smileapp.domains.easyfeed;

import com.cowrycode.smileapp.domains.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreastMilkData extends BaseEntity {
    private int breastfeedingduration;
    private String breastSide;
    private int PumpedQuantity_ML;
    private boolean isbottling;
    private boolean ispumping;
    private boolean isbreasting;
    private String userID;
    private String userName;
}
