package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;

public interface ApiCallService {
    ChatObjectModel callChatBot(ChatObjectModel chatObjectModel);
    String callChatGPT(String tex);
}
