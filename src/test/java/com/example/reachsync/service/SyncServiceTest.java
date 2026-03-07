package com.example.reachsync.service;

import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SyncServiceTest {

    @Test
    void testShouldDelegateToWorker() {
        SyncWorker syncWorker = Mockito.mock(SyncWorker.class);
        SyncRequest request = new SyncRequest();
        SyncResult expected = new SyncResult(true, "ok");

        Mockito.when(syncWorker.process(request)).thenReturn(expected);

        SyncService syncService = new SyncService(syncWorker);

        SyncResult result = syncService.syncRecord(request);

        assertTrue(result.isSuccess());
        assertEquals("ok", result.getMessage());
    }

}
