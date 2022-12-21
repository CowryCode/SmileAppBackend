package com.cowrycode.smileapp.controlllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public ResponseEntity<String> start(){
//        PriorityQueue<HolderObj> substrings = new PriorityQueue<HolderObj>(
//                (n1,n2)->n1.count - n2.count
//                );
        return new ResponseEntity<>("App have started", HttpStatus.OK);
    }

}


class HolderObj{
    String text;
    int count;
    public HolderObj(String text, int count) {
        this.text = text;
        this.count = count;
    }
}