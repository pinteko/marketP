package com.korsuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//    @EnableEurekaClient
public class CartApp {
    public static void main(String[] args) {
        SpringApplication.run(CartApp.class, args);
    }
}