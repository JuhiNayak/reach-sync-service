package com.example.reachsync.operation;

import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.model.SyncResult;
import com.example.reachsync.transformer.RecordTransformer;
import com.example.reachsync.transformer.TransformerRegistry;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeleteOperationHandler implements OperationHandler {

    private final TransformerRegistry transformerRegistry;

    public DeleteOperationHandler(TransformerRegistry transformerRegistry) {
        this.transformerRegistry = transformerRegistry;
    }

    @Override
    public OperationType getSupportedOperation() {
        return OperationType.DELETE;
    }

    @Override
    public SyncResult processRequest(SyncRequest syncRequest) {

        RecordTransformer transformer = transformerRegistry.getTransformer(
                syncRequest.getCrmType());

        Map<String, Object> payload = transformer.transformInternalRecord(syncRequest.getInternalRecord());
        System.out.println("Sending transformed payload :" + payload);

        return new SyncResult(true, "Record deleted successfully");
    }
}
