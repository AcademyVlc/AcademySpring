package com.example.demo.services.exceptions;

public class ChefNotFoundedException extends RuntimeException {
    public ChefNotFoundedException(String message) {
        super(message);
    }
}
