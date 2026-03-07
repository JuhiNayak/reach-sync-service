package com.example.reachsync.operation;

import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;

public interface OperationHandler {
    OperationType getSupportedOperation();

    SyncResult processRequest(SyncRequest syncRequest);
}
