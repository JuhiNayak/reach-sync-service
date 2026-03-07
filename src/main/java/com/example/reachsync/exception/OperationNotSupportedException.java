package com.example.reachsync.exception;

public class OperationNotSupportedException extends RuntimeException {

    public OperationNotSupportedException(String message) {
        super(message);
    }
}
