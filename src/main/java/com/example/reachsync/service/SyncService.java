package com.example.reachsync.service;

import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import org.springframework.stereotype.Service;

// Service layer between controller and worker
@Service
public class SyncService {
    private final SyncWorker syncWorker;

    public SyncService(SyncWorker syncWorker) {
        this.syncWorker = syncWorker;
    }

    public SyncResult syncRecord(SyncRequest request) {
        return syncWorker.process(request);
    }
}
