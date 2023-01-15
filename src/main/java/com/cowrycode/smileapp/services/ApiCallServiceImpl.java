package com.cowrycode.smileapp.services;

import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



import java.io.InputStreamReader;
import java.net.HttpURLConnection;


@Service
public class ApiCallServiceImpl implements ApiCallService {


    @Autowired
    Environment env;

    @Override
    public ChatObjectModel callChatBot(ChatObjectModel chatObjectModel) {
        String bot_url = env.getProperty("my-bot-url");
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
                ChatObjectModel botResponse = mapper.readValue(EntityUtils.toString(entity2), ChatObjectModel.class);
                EntityUtils.consume(entity2);
                return botResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String callChatGPT(String text) {
        String bot_url = env.getProperty("their-bot");
        String token = env.getProperty("their-bot-secret");


                // Set the payload
                String payload = "{\"model\": \"text-davinci-003\", \"prompt\": " + text+ ", \"max_tokens\": 1000, \"temperature\": 1.0}";


                // Send the POST request
        try {
           return sendPost(bot_url, payload, token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP POST request
    private String sendPost(String url, String payload, String bearerToken) throws Exception {

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            con.setRequestMethod("POST");

            // Set the content type to application/json
            con.setRequestProperty("Content-Type", "application/json");

            // Set the Bearer token in the authorization header
            con.setRequestProperty("Authorization", "Bearer " + "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");



            // Send the payload
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //con.disconnect();
            in.close();
            // Print the response
            System.out.println(response);

            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {

        }

    }


}
