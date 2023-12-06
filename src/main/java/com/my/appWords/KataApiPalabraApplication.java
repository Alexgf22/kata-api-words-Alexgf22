package com.my.appWords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.my.appWords.controllers", "com.my.appWords.repositories", "com.my.appWords.models", "com.my.appWords.services"})
public class KataApiPalabraApplication {

    public static void main(String[] args) {
        SpringApplication.run(KataApiPalabraApplication.class, args);
    }

}
