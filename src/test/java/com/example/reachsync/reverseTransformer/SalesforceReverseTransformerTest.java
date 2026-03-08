package com.example.reachsync.reverseTransformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesforceReverseTransformerTest {

    private final SalesforceReverseRecordTransformer transformer =
            new SalesforceReverseRecordTransformer();

    @Test
    void testShouldSupportSalesforceCrm() {
        assertEquals(CrmType.SALESFORCE, transformer.getSupportedCrm());
    }

    @Test
    void testTransformSalesforcePayload() {

        Map<String, Object> payload = Map.of(
                "First_Name_c", "John",
                "Last_Name_c", "Doe",
                "Email_c", "john@gmail.com");

        InternalRecord record = transformer.reverseTransform(payload);
        assertEquals("John", record.getFirstName());
        assertEquals("Doe", record.getLastName());
        assertEquals("john@gmail.com", record.getEmail());
    }
}
