package com.example.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class NewSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewSpringApplication.class, args);
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    @CacheEvict("largestPicture")
    public void refreshCache() {
        log.info("Removing NASA cache...");
    }
}
