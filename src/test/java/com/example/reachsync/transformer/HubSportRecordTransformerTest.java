package com.example.reachsync.transformer;

import com.example.reachsync.model.CrmType;
import com.example.reachsync.model.InternalRecord;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HubSportRecordTransformerTest {
    private final HubspotRecordTransformer transformer = new HubspotRecordTransformer();

    @Test
    void testShouldTransformRecordToHubSportSchema() {
        InternalRecord record =
                new InternalRecord("123", "John", "Doe", "john@gmail.com");

        Map<String, Object> result = transformer.transformInternalRecord(record);

        assertNotNull(result);

        Map<String, Object> properties = (Map<String, Object>) result.get("properties");

        assertEquals("John", properties.get("firstName"));
        assertEquals("Doe", properties.get("lastName"));
    }

    @Test
    void testShouldTransformRecordToHubSpotSchemaWithError() {
        InternalRecord record =
                new InternalRecord("123", "John", "Doe", "john@gmail.com");

        Map<String, Object> result = transformer.transformInternalRecord(record);

        assertNotNull(result);

        Map<String, Object> properties = (Map<String, Object>) result.get("properties");

        assertNotEquals("John", properties.get("First_Name_c"));
        assertEquals("Doe", properties.get("lastName"));
    }

    @Test
    void testShouldSupportHubSporCrm() {
        assertEquals(CrmType.HUBSPOT, transformer.getSupportedCrm());
    }
}
