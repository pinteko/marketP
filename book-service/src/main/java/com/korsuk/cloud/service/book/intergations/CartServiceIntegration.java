package com.korsuk.cloud.service.book.intergations;

import com.korsuk.cart.CartDto;
import com.korsuk.cloud.service.book.exceptions.CartServiceIntegrationException;
import com.korsuk.exceptions.CartServiceAppError;
import com.korsuk.exceptions.ResourceNotFoundException;
import com.korsuk.exceptions.ServerNotWorkingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getUserCart(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ServerNotWorkingException("Cart service not working")))
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                                body -> {
                                    if (body.getStatusCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())) {
                                        return new CartServiceIntegrationException("Incorrect request in cart service: cart not found");
                                    }
                                    if (body.getStatusCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())) {
                                        return new CartServiceIntegrationException("Incorrect request in cart service: cart is broken");
                                    }
                                    return new CartServiceIntegrationException("Incorrect request in cart service: unknown cause");
                                }
                        )
                )
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }

    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ServerNotWorkingException("Cart service not working")))
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new ResourceNotFoundException("Incorrect request")))
                .toBodilessEntity()
                .block();
    }
}
