package com.cowrycode.smileapp.services;

public interface FCMSenderService {
    boolean sendPushnotification(String title, String message, String reciepientMobileTOKEN);
}
