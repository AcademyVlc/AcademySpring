package com.example.demo.exception_handling.palestra.exceptions;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException(String message) {
        super(message);
    }
}
