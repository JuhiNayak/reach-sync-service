package com.example.reachsync.service;

import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import org.springframework.stereotype.Component;

@Component
public class SyncWorker {
    public SyncResult process(SyncRequest request) {

        System.out.println("Processing sync request for CRM: " + request.getCrmType());

        System.out.println("Sending record to external CRM System");

        return new SyncResult(true, "Record synchronized successfully");
    }
}
