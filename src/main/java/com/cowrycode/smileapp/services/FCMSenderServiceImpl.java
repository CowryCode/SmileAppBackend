package com.cowrycode.smileapp.services;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Notification;
import com.google.android.gcm.server.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class FCMSenderServiceImpl implements FCMSenderService {

    @Autowired
    Environment env;
    @Override
    public boolean sendPushnotification(String title, String msg, String reciepientMobileTOKEN) {
        String serverToken = env.getProperty("notification-token");

        String receiver = reciepientMobileTOKEN;

        try {
            Sender sender = new FCMSender(serverToken);
            Notification.Builder notification = new Notification.Builder("");
            notification.title(title);
            notification.body(msg);
            Message message = new  Message.Builder()
                    .collapseKey("message")
                    .timeToLive(3)
                    .addData("message", "SmileApp")
                    .notification(notification.build())
                    .priority(Message.Priority.HIGH)
                    .build();
            sender.send(message, receiver,1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

//        Thread t = new Thread(() -> {
//            try {
//                Sender sender = new FCMSender(serverToken);
//                Notification.Builder notification = new Notification.Builder("");
//                notification.title(title);
//                notification.body(msg);
//                Message message = new  Message.Builder()
//                        .collapseKey("message")
//                        .timeToLive(3)
//                        .addData("message", "SmileApp")
//                        .notification(notification.build())
//                        .priority(Message.Priority.HIGH)
//                        .build();
//               sender.send(message, receiver,1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        t.start();
//        try {
//            t.join();
//            return true;
//        }
//        catch (InterruptedException iex) {
//            iex.printStackTrace();
//            return false;
//        }
    }
}
