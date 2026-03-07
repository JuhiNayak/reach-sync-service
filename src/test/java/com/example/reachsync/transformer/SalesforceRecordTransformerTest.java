package com.example.reachsync.transformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SalesforceRecordTransformerTest {
    private final SalesforceRecordTransformer transformer = new SalesforceRecordTransformer();

    @Test
    void testShouldTransformRecordToSalesforceSchema() {
        InternalRecord record =
                new InternalRecord("123", "John", "Doe", "john@gmail.com");

        Map<String, Object> result = transformer.transformInternalRecord(record);

        assertEquals("John", result.get("First_Name_c"));
        assertEquals("Doe", result.get("Last_Name_c"));
    }

    @Test
    void testShouldTransformRecordToSalesforceSchemaWithError() {
        InternalRecord record =
                new InternalRecord("123", "John", "Doe", "john@gmail.com");

        Map<String, Object> result = transformer.transformInternalRecord(record);

        assertNotEquals("John", result.get("firstName"));
        assertEquals("Doe", result.get("Last_Name_c"));
    }

    @Test
    void testShouldSupportSalesForceCrm() {
        assertEquals(CrmType.SALESFORCE, transformer.getSupportedCrm());
    }
}
