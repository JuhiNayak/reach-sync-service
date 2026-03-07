package com.example.reachsync.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerNotFoundExceptionTest {

    @Test
    void testShouldCreateExceptionWithMessage() {
        String message = "Transformer not supported";

        TransformerNotFoundException exception = new TransformerNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}
