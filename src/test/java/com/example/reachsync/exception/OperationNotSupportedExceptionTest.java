package com.example.reachsync.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationNotSupportedExceptionTest {

    @Test
    void testShouldCreateExceptionWithMessage() {
        String message = "Operation not supported";

        OperationNotSupportedException exception = new OperationNotSupportedException(message);

        assertEquals(message, exception.getMessage());
    }
}
