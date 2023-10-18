package com.example.demo1.controllor;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class simpleParameter {
    @RequestMapping("/simpleParameter")
    public String simpleParameter(String name, Integer age){
        /*String name = request.getParameter("name");
        String agestr = request.getParameter("age");
        int age = Integer.parseInt(agestr);*/
        System.out.println("name : "+age);
        return "OK";
    }
}
