package com.cowrycode.smileapp.models.metamodels;

import com.cowrycode.smileapp.models.MyTribeMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnreadTribeMessagesDTO {
    List<MyTribeMessageDTO> messages;
}
