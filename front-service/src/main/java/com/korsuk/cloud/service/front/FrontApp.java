package com.korsuk.cloud.service.front;

import com.korsuk.core.NovelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class FrontApp {

    @Autowired
    private RestTemplate restTemplate;


    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }

    @GetMapping("/api/v1/novels")
    public Page<NovelDto> getNovelsFromNovelService() {
        return restTemplate.getForObject("http://book-service/api/v1/novels", Page.class);
    }
}