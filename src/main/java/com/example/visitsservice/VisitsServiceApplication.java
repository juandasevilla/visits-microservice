package com.example.visitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VisitsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisitsServiceApplication.class, args);
    }

}
