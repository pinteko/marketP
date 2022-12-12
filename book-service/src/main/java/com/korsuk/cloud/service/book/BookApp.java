package com.korsuk.cloud.service.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


    @SpringBootApplication
//    @EnableEurekaClient
    public class BookApp {
        public static void main(String[] args) {
            SpringApplication.run(BookApp.class, args);
        }
    }

