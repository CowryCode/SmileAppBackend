package com.cowrycode.smileapp.controlllers;

import com.cowrycode.smileapp.controlllers.ChatController.ChatObjectModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public ResponseEntity<String> start(){

//        System.out.println("LocalTime : " + LocalDate.now());
//
//        List<List<Integer>> arr = new ArrayList<>();
//
//        List<Integer> ar1 = new ArrayList<>();
//        ar1.add(1);
//        ar1.add(2);
//        ar1.add(3);
//        List<Integer> ar2 = new ArrayList<>();
//        ar2.add(4);
//        ar2.add(5);
//        ar2.add(6);
//        arr.add(ar1);
//        arr.add(ar2);
//
//        ChatObjectModel chatObjectModel = new ChatObjectModel();
//        chatObjectModel.setChatContent("This is the content");
//        chatObjectModel.setChatHistory(arr);
//
//        System.out.println(" ARRAY IN STRING : " + chatObjectModel.getChatHistory().toString());
//        System.out.println(" Object To STRING : " + chatObjectModel.toString());

        return new ResponseEntity<>("App have started", HttpStatus.OK);
    }

}
