package com.example.reachsync.transformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;

import java.util.Map;

public interface RecordTransformer {
    CrmType getSupportedCrm();
    
    Map<String, Object> transformInternalRecord(InternalRecord record);
}
