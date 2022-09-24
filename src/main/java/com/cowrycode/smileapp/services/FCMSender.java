package com.cowrycode.smileapp.services;


import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FCMSender extends Sender {
    public FCMSender(String key) {
        super(key);
    }

    // FCM  = Firebase Cloud Messaging
    @Override
    protected HttpURLConnection getConnection(String url) throws IOException {
        String fcmUrl = "https://fcm.googleapis.com/fcm/send";
        return (HttpURLConnection) new URL(fcmUrl).openConnection();
    }

}
