package com.example.reachsync.reverseTransformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SalesforceReverseRecordTransformer implements ReverseRecordTransformer{
    @Override
    public CrmType getSupportedCrm() {
        return CrmType.SALESFORCE;
    }

    @Override
    public InternalRecord reverseTransform(Map<String, Object> payload) {
        return new InternalRecord(
                null,
                (String) payload.get("First_Name_c"),
                (String) payload.get("Last_Name_c"),
                (String) payload.get("email_c"));
    }
}
