package com.example.app.exception;

public class IncorrectPictureException extends RuntimeException {
    public IncorrectPictureException() {
        super("Incorrect Picture!");
    }
}
