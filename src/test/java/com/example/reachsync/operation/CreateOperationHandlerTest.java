package com.example.reachsync.operation;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.OperationType;
import com.example.reachsync.model.InternalRecord;
import com.example.reachsync.model.SyncResult;
import com.example.reachsync.model.SyncRequest;
import com.example.reachsync.transformer.RecordTransformer;
import com.example.reachsync.transformer.TransformerRegistry;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateOperationHandlerTest {

    @Test
    void testShouldCreateRecordSuccessfully() {
        TransformerRegistry registry = Mockito.mock(TransformerRegistry.class);
        RecordTransformer transformer = Mockito.mock(RecordTransformer.class);

        when(registry.getTransformer(CrmType.SALESFORCE)).thenReturn(transformer);
        when(transformer.transformInternalRecord(any()))
                .thenReturn(Map.of("email", "john@gmail.com"));

        InternalRecord internalRecord = new InternalRecord("123", "John", "Doe", "john@gmail.com");

        CreateOperationHandler handler = new CreateOperationHandler(registry);
        SyncRequest request = new SyncRequest(
                CrmType.SALESFORCE,
                OperationType.CREATE,
                internalRecord);

        SyncResult result = handler.processRequest(request);

        verify(registry, times(1)).getTransformer(CrmType.SALESFORCE);
        verify(transformer, times(1)).transformInternalRecord(internalRecord);

        assertTrue(result.isSuccess());
        assertEquals("Record created successfully", result.getMessage());
    }
}
