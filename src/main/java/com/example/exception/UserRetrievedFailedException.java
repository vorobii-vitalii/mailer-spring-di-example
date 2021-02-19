package com.example.exception;

public class UserRetrievedFailedException extends RuntimeException {

    public UserRetrievedFailedException() {}

    public UserRetrievedFailedException(String message) {
        super(message);
    }

    public UserRetrievedFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
