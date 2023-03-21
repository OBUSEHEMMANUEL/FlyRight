package com.example.flyright;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FlyRightApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlyRightApplication.class, args);
    }

}
