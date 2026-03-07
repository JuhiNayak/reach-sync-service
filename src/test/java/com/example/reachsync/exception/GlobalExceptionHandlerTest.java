package com.example.reachsync.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testShouldHandleOperationNotSupportedException() {
        String message = "Operation not supported";

        OperationNotSupportedException exception = new OperationNotSupportedException(message);

        ResponseEntity<String> response = handler.handleCrmNotSupported(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void testShouldHandleGenericException() {

        Exception exception = new Exception("error");

        ResponseEntity<String> response = handler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occured: " + exception.getMessage(), response.getBody());
    }
}
