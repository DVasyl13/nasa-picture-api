package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class NewSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewSpringApplication.class, args);
    }
}
