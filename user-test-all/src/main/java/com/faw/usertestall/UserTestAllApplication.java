package com.faw.usertestall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class UserTestAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTestAllApplication.class, args);
    }

}
