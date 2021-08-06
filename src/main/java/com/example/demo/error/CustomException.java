package com.example.demo.error;

public class CustomException extends Exception {
    private static final String DEFAULT_ERROR_MSG = "Error in some validation";

    public CustomException() {
        super(DEFAULT_ERROR_MSG);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
