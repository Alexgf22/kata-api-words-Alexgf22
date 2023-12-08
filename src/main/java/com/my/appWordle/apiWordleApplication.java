package com.my.appWordle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.my.appWordle.controllers", "com.my.appWordle.repositories", "com.my.appWordle.models", "com.my.appWordle.services"})
public class apiWordleApplication {

    public static void main(String[] args) {
        SpringApplication.run(apiWordleApplication.class, args);
    }

}
