package com.example.reachsync.service;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class SyncWorkerTest {

    private final SyncWorker syncWorker = new SyncWorker();

    @Test
    void testShouldProcessSyncRequestSuccessfully() {
        InternalRecord record = new InternalRecord(
                "123",
                "John",
                "Doe",
                "john@gmail.com");

        SyncRequest request = new SyncRequest(CrmType.SALESFORCE, OperationType.CREATE, record);

        SyncResult result = syncWorker.process(request);

        assertTrue(result.isSuccess());
        assertEquals("Record synchronized successfully", result.getMessage());
    }

    @Test
    void testShouldHandleNullRecord() {
        SyncRequest request = new SyncRequest(CrmType.SALESFORCE, OperationType.CREATE, null);

        SyncResult result = syncWorker.process(request);

        assertNotNull(result);
    }
}
