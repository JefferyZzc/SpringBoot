package com.example.demo1.controllor;

import com.example.demo1.Pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class complexPojo {
    @RequestMapping("/complexPojo")
    public String simplePojo(User user){
        System.out.println(user);
        return "OK";
    }
}