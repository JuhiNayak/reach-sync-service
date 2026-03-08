package com.example.reachsync.controller;

import com.example.reachsync.model.CrmWebhookEvent;
import com.example.reachsync.service.InboundSyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm/webhook")
public class InboundSyncController {

    private final InboundSyncService service;

    public InboundSyncController(InboundSyncService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> receiveEvent(@RequestBody CrmWebhookEvent event)  {
        service.processEvent(event);
        return ResponseEntity.ok("Webhook processed");
    }
}
