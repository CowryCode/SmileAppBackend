package com.cowrycode.smileapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnrepliedTribeCalls {
    List<EmpathyRequestDTO> msgcalls;
}
