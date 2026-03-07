package com.example.reachsync.operation;

import com.example.reachsync.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateOperationHandlerTest {

    private final CreateOperationHandler handler = new CreateOperationHandler();

    @Test
    void testShouldCreateRecordSuccessfully() {
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                OperationType.CREATE,
                new InternalRecord("123", "John", "Doe", "john@gmail.com"));

        SyncResult result = handler.processRequest(request);

        assertTrue(result.isSuccess());
        assertEquals("Record created successfully", result.getMessage());
    }
}
