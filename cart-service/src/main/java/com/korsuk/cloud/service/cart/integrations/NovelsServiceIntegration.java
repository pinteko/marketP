package com.korsuk.cloud.service.cart.integrations;

import com.korsuk.core.NovelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NovelsServiceIntegration {

    private final RestTemplate restTemplate;

    @Value("${integrations.book-service.url}")
    private String novelServiceUrl;

    public Optional<NovelDto> findById(Long id) {
        NovelDto novelDto = restTemplate.getForObject(novelServiceUrl + "/api/v1/novels/" + id, NovelDto.class);
        return Optional.ofNullable(novelDto);
    }
}
