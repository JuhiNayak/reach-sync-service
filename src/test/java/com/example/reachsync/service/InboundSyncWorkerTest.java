package com.example.reachsync.service;

import com.example.reachsync.exception.TransformerNotFoundException;
import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.CrmWebhookEvent;
import com.example.reachsync.model.InternalRecord;
import com.example.reachsync.reverseTransformer.ReverseRecordTransformer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class InboundSyncWorkerTest {

    @Test
    void testShouldProcessInboundEventSuccessfully() {
        ReverseRecordTransformer transformer = mock(ReverseRecordTransformer.class);

        when(transformer.getSupportedCrm()).thenReturn(CrmType.SALESFORCE);
        when(transformer.reverseTransform(any())).thenReturn(
                new InternalRecord("1", "John", "Doe", "john@google.com"));

        InboundSyncWorker worker =
                new InboundSyncWorker(List.of(transformer));

        CrmWebhookEvent event = new CrmWebhookEvent();
        event.setCrmType(CrmType.SALESFORCE);
        event.setPayload(Map.of("Fist_Name_c", "John"));

        worker.process(event);

        verify(transformer, times(1)).reverseTransform(event.getPayload());
    }

    @Test
    void testShouldThrowExceptionForUnsupportedCrm() {
        InboundSyncWorker worker =
                new InboundSyncWorker(List.of());

        CrmWebhookEvent event = new CrmWebhookEvent();
        event.setCrmType(CrmType.SALESFORCE);

        assertThrows(
                TransformerNotFoundException.class,
                () -> worker.process(event));
    }
}
