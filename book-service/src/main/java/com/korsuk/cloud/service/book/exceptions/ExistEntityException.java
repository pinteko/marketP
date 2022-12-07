package com.korsuk.cloud.service.book.exceptions;

public class ExistEntityException extends RuntimeException{

    public ExistEntityException(String message) {
        super(message);
    }
}
