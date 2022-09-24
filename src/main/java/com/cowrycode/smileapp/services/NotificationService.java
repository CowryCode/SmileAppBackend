package com.cowrycode.smileapp.services;

public interface NotificationService {
    boolean sendPushnotification(String title, String message, String reciepientMobileTOKEN);
}
