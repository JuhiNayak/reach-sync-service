package com.example.reachsync.operation;

import com.example.reachsync.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteOperationHandlerTest {
    private final DeleteOperationHandler handler = new DeleteOperationHandler();

    @Test
    void testShouldUpdateRecordSuccessfully() {
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                OperationType.DELETE,
                new InternalRecord("123", "John", "Doe", "john@gmail.com"));

        SyncResult result = handler.processRequest(request);

        assertTrue(result.isSuccess());
        assertEquals("Record deleted successfully", result.getMessage());
    }
}
