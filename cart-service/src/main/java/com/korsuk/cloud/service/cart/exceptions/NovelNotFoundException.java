package com.korsuk.cloud.service.cart.exceptions;

public class NovelNotFoundException extends RuntimeException{
    public NovelNotFoundException(String message) {
        super(message);
    }
}
