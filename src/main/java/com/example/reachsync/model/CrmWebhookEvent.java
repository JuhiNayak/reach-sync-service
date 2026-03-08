package com.example.reachsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrmWebhookEvent {

    @JsonProperty(required = true)
    private CrmType crmType;
    @JsonProperty(required = true)
    private OperationType operationType;
    @JsonProperty(required = true)
    private Map<String, Object> payload;
}
