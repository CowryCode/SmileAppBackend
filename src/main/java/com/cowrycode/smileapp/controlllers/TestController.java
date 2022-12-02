package com.cowrycode.smileapp.controlllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public ResponseEntity<String> start(){
        PriorityQueue<HolderObj> substrings = new PriorityQueue<HolderObj>(
                (n1,n2)->n1.count - n2.count
                );
        HashSet<Character> checker = new HashSet<>();

        String text = "nndNfdfdf";

        char[] vals = text.toCharArray();

        int counter = 0;
        String strbulder = "";
        for (int i =0; i < vals.length; i++){
            if(!checker.contains(vals[i])){
                checker.add(vals[i]);
                strbulder = strbulder + vals[i];
                counter++;
            }else {
                if(counter > 0){
                    HolderObj obj =  new HolderObj(strbulder, counter);
                    substrings.add(obj);
                    strbulder = "";
                    counter = 0;
                    checker.clear();
                }
                checker.add(vals[i]);
                strbulder = strbulder + vals[i];
                counter++;
            }

        }

        while (substrings.size() > 0){
            HolderObj vv = substrings.poll();
            System.out.println(" Sub : " + vv.text + "  Count : " + vv.count);
        }


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