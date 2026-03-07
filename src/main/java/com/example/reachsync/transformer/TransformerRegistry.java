package com.example.reachsync.transformer;

import com.example.reachsync.exception.TransformerNotFoundException;
import com.example.reachsync.model.CrmType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TransformerRegistry {
    private final Map<CrmType, RecordTransformer> transformerMap;

    public TransformerRegistry(List<RecordTransformer> transformers) {
        transformerMap = transformers.stream()
                .collect(Collectors.toMap(
                        RecordTransformer::getSupportedCrm,
                        Function.identity()));
    }

    public RecordTransformer getTransformer(CrmType crmType) {

        RecordTransformer transformer = transformerMap.get(crmType);
        if (transformer == null) {
            throw new TransformerNotFoundException(
                    "No transformer found from CRM type: " +  crmType);
        }
        return transformerMap.get(crmType);
    }
}
