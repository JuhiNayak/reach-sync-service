package com.example.reachsync.reverseTransformer;

import com.example.reachsync.exception.TransformerNotFoundException;
import com.example.reachsync.model.CrmType;
import com.example.reachsync.transformer.RecordTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ReverseTransformerRegistry {

    private final Map<CrmType, ReverseRecordTransformer> transformerMap;

    public ReverseTransformerRegistry(List<ReverseRecordTransformer> transformers) {
        transformerMap = transformers.stream()
                .collect(Collectors.toMap(
                        ReverseRecordTransformer::getSupportedCrm,
                        Function.identity()));
    }

    public ReverseRecordTransformer getTransformer(CrmType crmType) {

        ReverseRecordTransformer transformer = transformerMap.get(crmType);
        if (transformer == null) {
            throw new TransformerNotFoundException(
                    "No transformer found from CRM type: " +  crmType);
        }
        return transformerMap.get(crmType);
    }
}
