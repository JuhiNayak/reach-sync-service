package com.example.reachsync.operation;

import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import org.springframework.stereotype.Component;

@Component
public class DeleteOperationHandler implements OperationHandler {

    @Override
    public OperationType getSupportedOperation() {
        return OperationType.DELETE;
    }

    @Override
    public SyncResult processRequest(SyncRequest syncRequest) {
        return new SyncResult(true, "Record deleted successfully");
    }
}
