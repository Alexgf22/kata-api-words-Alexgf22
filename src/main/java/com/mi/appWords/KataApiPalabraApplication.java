package com.mi.appWords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mi.appWords.controllers", "com.mi.appWords.repositories", "com.mi.appWords.models", "com.mi.appWords.services"})
public class KataApiPalabraApplication {

    public static void main(String[] args) {
        SpringApplication.run(KataApiPalabraApplication.class, args);
    }

}
