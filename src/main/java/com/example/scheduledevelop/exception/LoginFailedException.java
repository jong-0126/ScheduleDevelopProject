package com.example.scheduledevelop.exception;

import lombok.Getter;

@Getter
public class LoginFailedException extends RuntimeException{

    private final String message;

    public LoginFailedException(String message) {
        this.message = message;
    }
}
