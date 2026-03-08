package com.example.reachsync.service;

import com.example.reachsync.model.CrmWebhookEvent;
import org.springframework.stereotype.Service;

@Service
public class InboundSyncService {
    private final InboundSyncWorker worker;

    public InboundSyncService(InboundSyncWorker worker) {
        this.worker = worker;
    }

    public void processEvent(CrmWebhookEvent event) {
        worker.process(event);
    }
}
