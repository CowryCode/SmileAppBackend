package com.cowrycode.smileapp.services.easyfeed.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyFeedUserStatus {
    private int averageNumberOfBreastFeeding;
    private int averageNumberOfPumping;
    private int averageNumberOfBottling;
}
