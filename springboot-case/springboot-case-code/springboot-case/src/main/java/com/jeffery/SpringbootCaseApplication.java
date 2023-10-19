package com.jeffery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class SpringbootCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCaseApplication.class, args);
    }

}
