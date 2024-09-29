package com.knowprogram.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppWithCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWithCacheApplication.class, args);
    }

}
