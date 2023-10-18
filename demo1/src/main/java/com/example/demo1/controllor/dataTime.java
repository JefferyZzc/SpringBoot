package com.example.demo1.controllor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class dataTime {
    @RequestMapping("/updateTime")
    public String dateTime(@DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:ss") LocalDateTime updateTime){
        System.out.println(updateTime);
        return "OK";
    }
}
