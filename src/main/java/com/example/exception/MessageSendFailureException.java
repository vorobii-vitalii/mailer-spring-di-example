package com.example.exception;

public class MessageSendFailureException extends RuntimeException {

    public MessageSendFailureException() {}

    public MessageSendFailureException(String message) {
        super(message);
    }

    public MessageSendFailureException(String message, Throwable cause) {
        super(message, cause);
    }

}
