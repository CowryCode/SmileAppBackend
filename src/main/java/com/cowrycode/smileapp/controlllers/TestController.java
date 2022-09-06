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
        return new ResponseEntity<>("App have stated", HttpStatus.OK);
    }
}
