package com.example.reachsync.service;

import com.example.reachsync.exception.OperationNotSupportedException;
import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import com.example.reachsync.operation.OperationHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SyncWorker {

    private final Map<OperationType, OperationHandler> handlerMap;

    public SyncWorker(List<OperationHandler> handlers) {
        handlerMap = handlers.stream()
                .collect(Collectors.toMap(
                        OperationHandler::getSupportedOperation, Function.identity()));
    }
    public SyncResult process(SyncRequest request) {

        OperationHandler handler = handlerMap.get(request.getOperationType());

        if (handler == null) {
            throw new OperationNotSupportedException(
                    "Operation not supported: " + request.getOperationType());
        }

        System.out.println("Processing sync request for CRM: " + request.getCrmType());

        return handler.processRequest(request);
    }
}
