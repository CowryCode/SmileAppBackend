package com.cowrycode.smileapp.models.easyfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightDataDTO {
    private Long id;
    private String userID;
    private String weight;
}
