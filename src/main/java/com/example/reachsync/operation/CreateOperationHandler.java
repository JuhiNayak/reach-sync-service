package com.example.reachsync.operation;

import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import com.example.reachsync.rateLimiter.RateLimiter;
import com.example.reachsync.transformer.RecordTransformer;
import com.example.reachsync.transformer.TransformerRegistry;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CreateOperationHandler implements OperationHandler {

    private final TransformerRegistry transformerRegistry;
    private final RateLimiter rateLimiter;

    public CreateOperationHandler(TransformerRegistry transformerRegistry, RateLimiter rateLimiter) {
        this.transformerRegistry = transformerRegistry;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public OperationType getSupportedOperation() {
        return OperationType.CREATE;
    }

    @Override
    public SyncResult processRequest(SyncRequest syncRequest) {

        if (rateLimiter.allowRequest(syncRequest.getCrmType().toString())) {
            RecordTransformer transformer = transformerRegistry.getTransformer(
                    syncRequest.getCrmType());

            Map<String, Object> payload = transformer.transformInternalRecord(syncRequest.getInternalRecord());
            System.out.println("Sending transformed payload :" + payload);
            return new SyncResult(true, "Record created successfully");
        } else {
            return new SyncResult(false, "Record failed to create due to rate-limiter limit exceed");
        }


    }
}
