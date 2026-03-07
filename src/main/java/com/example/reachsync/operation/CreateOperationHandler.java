package com.example.reachsync.operation;

import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import org.springframework.stereotype.Component;

@Component
public class CreateOperationHandler implements OperationHandler {
    @Override
    public OperationType getSupportedOperation() {
        return OperationType.CREATE;
    }

    @Override
    public SyncResult processRequest(SyncRequest syncRequest) {
        return new SyncResult(true, "Record created successfully");
    }
}
