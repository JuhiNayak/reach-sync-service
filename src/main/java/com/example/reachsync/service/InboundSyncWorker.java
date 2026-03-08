package com.example.reachsync.service;

import com.example.reachsync.exception.OperationNotSupportedException;
import com.example.reachsync.exception.TransformerNotFoundException;
import com.example.reachsync.model.*;
import com.example.reachsync.operation.OperationHandler;
import com.example.reachsync.reverseTransformer.ReverseRecordTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class InboundSyncWorker {
    private final Map<CrmType, ReverseRecordTransformer> transformerMap;

    public InboundSyncWorker(List<ReverseRecordTransformer> transformers) {
        transformerMap = transformers.stream()
                .collect(Collectors.toMap(
                        ReverseRecordTransformer::getSupportedCrm, Function.identity()));
    }
    public void process(CrmWebhookEvent event) {

        ReverseRecordTransformer transformer = transformerMap.get(event.getCrmType());

        if (transformer == null) {
            throw new TransformerNotFoundException(
                    "Transformation not supported: " + event.getCrmType());
        }
        InternalRecord record = transformer.reverseTransform(event.getPayload());

        System.out.println("Upding internal system with record :" + record);
    }
}
