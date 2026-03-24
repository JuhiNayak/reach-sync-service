package com.example.reachsync.service;

import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import com.example.reachsync.rateLimiter.RateLimiter;
import com.example.reachsync.rateLimiter.TokenBucketRateLimiter;
import org.springframework.stereotype.Service;

// Service layer between controller and worker
@Service
public class SyncService {
    private final SyncWorker syncWorker;
    public  final RateLimiter rateLimiter;

    public SyncService(SyncWorker syncWorker) {
        this.syncWorker = syncWorker;
        this.rateLimiter = new TokenBucketRateLimiter(100, 50);
    }

    public SyncResult syncRecord(SyncRequest request) {
        return syncWorker.process(request);
    }
}
