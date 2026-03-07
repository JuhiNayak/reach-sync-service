package com.example.reachsync.service;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.reachsync.operation.OperationHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class SyncWorkerTest {

    @Test
    void testShouldProcessSyncRequestSuccessfully() {
        OperationHandler handler = Mockito.mock(OperationHandler.class);

        when(handler.getSupportedOperation()).thenReturn(OperationType.CREATE);
        when(handler.processRequest(any())).thenReturn(new SyncResult(true, "created"));

        SyncWorker worker = new SyncWorker(List.of(handler));
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                OperationType.CREATE,
                new InternalRecord("123", "John", "Doe", "john@gmail.com"));

        SyncResult result = worker.process(request);

        assertTrue(result.isSuccess());
        verify(handler, times(1)).processRequest(request);
    }

    @Test
    void testShouldHandleNullRecord() {
        OperationHandler handler = Mockito.mock(OperationHandler.class);

        when(handler.getSupportedOperation()).thenReturn(null);
        when(handler.processRequest(any())).thenReturn(new SyncResult(false, "unsupported operation"));

        SyncWorker worker = new SyncWorker(List.of(handler));
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                null,
                new InternalRecord("123", "John", "Doe", "john@gmail.com"));

        SyncResult result = worker.process(request);

        assertFalse(result.isSuccess());
        verify(handler, times(1)).processRequest(request);
    }

    @Test
    void testShouldReturnFailureForUnsupportedOperation() {
        SyncWorker worker = new SyncWorker(List.of());
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                OperationType.CREATE,
                new InternalRecord("123", "John", "Doe", "john@gmail.com"));

        SyncResult result = worker.process(request);

        assertFalse(result.isSuccess());
    }
}
