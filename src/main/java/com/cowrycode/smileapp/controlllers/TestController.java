package com.cowrycode.smileapp.controlllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/")
public class TestController {
    HashSet<Integer> set = new HashSet<>();
    @GetMapping
    public ResponseEntity<String> start(){

        char ar[] = new char[] {'.','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        add(1);
        add(2);
        add(3);
        System.out.println("Find 4: " + find(4));
        System.out.println("Find 5: " + find(7));
        return new ResponseEntity<>("App have started", HttpStatus.OK);
    }


    public void add(int number) {
        set.add(number);
    }

    public boolean find(int value) {
        if(value > 200000 || value < -200000){
            return false;
        }

        for(Integer x : set){
            if(set.contains(value - x)){
                return true;
            }
        }
        return false;
    }
}
