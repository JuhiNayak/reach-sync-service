package com.example.reachsync.reverseTransformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ReverseRecordTransformer {

    CrmType getSupportedCrm();

    InternalRecord reverseTransform(Map<String, Object> payload);
}
