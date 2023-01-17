package com.korsuk.cloud.service.cart.integrations;

import com.korsuk.cart.CartDto;
import com.korsuk.core.NovelDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import com.korsuk.exceptions.ServerNotWorkingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NovelsServiceIntegration {

    private final WebClient novelServiceWebClient;

    public Optional<NovelDto> findById(Long id) {
        NovelDto novelDto = novelServiceWebClient.get()
                .uri("/api/v1/novels/")
                .header("id", String.valueOf(id))
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ServerNotWorkingException("Novel service not working")))
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new ResourceNotFoundException("Incorrect request")))
                .bodyToMono(NovelDto.class)
                .block();
        return Optional.ofNullable(novelDto);
    }

}
