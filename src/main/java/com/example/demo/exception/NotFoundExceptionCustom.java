package com.example.demo.exception;

public class NotFoundExceptionCustom extends RuntimeException {
    public NotFoundExceptionCustom(String message) {
        super(message);
    }
}
