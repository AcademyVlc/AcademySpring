package com.example.demo.exception_handling.palestra.exceptions;

public class RoomFullException extends RuntimeException {
    public RoomFullException(String message) {
        super(message);
    }
}
