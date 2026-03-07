package com.example.reachsync.transformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HubspotRecordTransformer implements RecordTransformer{
    @Override
    public CrmType getSupportedCrm() {
        return CrmType.HUBSPOT;
    }

    @Override
    public Map<String, Object> transformInternalRecord(InternalRecord record) {
        Map<String, Object> properties = new HashMap<>();

        properties.put("firstName", record.getFirstName());
        properties.put("lastName", record.getLastName());
        properties.put("email", record.getEmail());

        Map<String, Object> payload = new HashMap<>();
        payload.put("properties", properties);
        return payload;
    }
}
