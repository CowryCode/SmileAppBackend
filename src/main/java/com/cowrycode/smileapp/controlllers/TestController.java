package com.cowrycode.smileapp.controlllers;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public ResponseEntity<String> start(){
        PriorityQueue<C1> queue = new PriorityQueue<>(
                (n1, n2) -> n2.datesubmitted.compareTo(n1.datesubmitted)
        );
        queue.add(new C1(1, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,6,0))));
        queue.add(new C1(2, LocalDateTime.of(LocalDate.now(), LocalTime.of(6,6,0))));
        queue.add(new C1(3, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(10,6,0))));
        queue.add(new C1(4, LocalDateTime.of(LocalDate.now(), LocalTime.of(8,6,0))));
        queue.add(new C1(5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9,6,0))));
        queue.add(new C1(6, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(10,6,0))));
        queue.add(new C1(7, LocalDateTime.of(LocalDate.now(), LocalTime.of(11,6,0))));
        queue.add(new C1(8, LocalDateTime.of(LocalDate.now(), LocalTime.of(12,6,0))));
        queue.add(new C1(9, LocalDateTime.of(LocalDate.now(), LocalTime.of(13,6,0))));
        queue.add(new C1(10, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(10,7,0))));
        queue.add(new C1(11, LocalDateTime.of(LocalDate.now(), LocalTime.of(15,6,0))));
        queue.add(new C1(12, LocalDateTime.of(LocalDate.now(), LocalTime.of(16,6,0))));
        queue.add(new C1(13, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,6,0))));

        int index = 1;
        while (index <= 5){
            C1 c1 = queue.poll();
            index++;
            System.out.println("ID : " + c1.id + " Date : " + c1.datesubmitted.toString());
        }

        return new ResponseEntity<>("App have started", HttpStatus.OK);
    }

}


class C1 {
    int id;
    LocalDateTime datesubmitted;
    C1(int id, LocalDateTime dateTime){
        this.id = id;
        this.datesubmitted = dateTime;
    }

}
