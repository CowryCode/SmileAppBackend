package com.cowrycode.smileapp.controlllers.ChatController;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatObjectModel {
    String chatContent;
    List<List<Integer>> chatHistory; /*Chat History is a two dimensional array of integers*/
 }
