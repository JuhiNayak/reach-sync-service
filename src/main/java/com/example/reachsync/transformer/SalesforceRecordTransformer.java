package com.example.reachsync.transformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SalesforceRecordTransformer implements RecordTransformer {
    @Override
    public CrmType getSupportedCrm() {
        return CrmType.SALESFORCE;
    }

    @Override
    public Map<String, Object> transformInternalRecord(InternalRecord record) {
        Map<String, Object> payload = new HashMap<>();
        
        payload.put("First_Name_c", record.getFirstName());
        payload.put("Last_Name_c", record.getLastName());
        payload.put("email_c", record.getEmail());
        return payload;
    }
}
