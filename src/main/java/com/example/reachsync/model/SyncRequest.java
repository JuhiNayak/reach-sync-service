package com.example.reachsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncRequest {
    @JsonProperty(required = true)
    private CrmType crmType;
    @JsonProperty(required = true)
    private OperationType operationType;
    @JsonProperty(required = true)
    private InternalRecord internalRecord;
}
