package com.example.reachsync.operation;

import com.example.reachsync.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateOperationHandlerTest {

    private final UpdateOperationHandler handler = new UpdateOperationHandler();

    @Test
    void testShouldUpdateRecordSuccessfully() {
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                OperationType.UPDATE,
                new InternalRecord("123", "John", "Doe", "john@gmail.com"));

        SyncResult result = handler.processRequest(request);

        assertTrue(result.isSuccess());
        assertEquals("Record updated successfully", result.getMessage());
    }
}
