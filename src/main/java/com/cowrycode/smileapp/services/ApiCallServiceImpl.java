package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApiCallServiceImpl implements ApiCallService {


    @Autowired
    Environment env;
    @Override
    public ChatObjectModel callChatBot(ChatObjectModel chatObjectModel) {
            String bot_url = env.getProperty("bot-url");
            /*GET METHOD CALL TEMPLATE*/
            try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
              //  final HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts");

             //   httpGet.setHeader("Authorization", "Bearer U/G2ysIY2mpk=");
//                try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
//                    System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
//                    final HttpEntity entity1 = response1.getEntity();
//
//                    // CONVERT THE RETURNED Json to JavaObject
//                    ObjectMapper mapper = new ObjectMapper();
//                    PsychoEducationDTO psychoEducationDTO  = mapper.readValue(EntityUtils.toString(entity1), PsychoEducationDTO.class);
//                    System.out.println("Converted id : " + psychoEducationDTO.getId() );
//                    EntityUtils.consume(entity1);
//                }

                /*GET METHOD CALL TEMPLATE*/
//                final HttpPost httpPost = new HttpPost("http://httpbin.org/post");
//                final List<NameValuePair> nvps = new ArrayList<>();
//                nvps.add(new BasicNameValuePair("username", "vip"));
//                nvps.add(new BasicNameValuePair("password", "secret"));
//                httpPost.setEntity(new UrlEncodedFormEntity(nvps));


                final HttpPost httpPost = new HttpPost(bot_url);
                final List<NameValuePair> nvps = new ArrayList<>();
                nvps.add(new BasicNameValuePair("chat", chatObjectModel.getChatContent()));
                nvps.add(new BasicNameValuePair("history", chatObjectModel.getChatHistory().toString()));
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));



                try (final CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
                    System.out.println(response2.getCode() + " " + response2.getReasonPhrase());
                    final HttpEntity entity2 = response2.getEntity();

                    // CONVERT THE RETURNED Json to JavaObject
                    ObjectMapper mapper = new ObjectMapper();
                    ChatObjectModel botResponse  = mapper.readValue(EntityUtils.toString(entity2), ChatObjectModel.class);
                    EntityUtils.consume(entity2);
                    return botResponse;
                }
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

    }
}
