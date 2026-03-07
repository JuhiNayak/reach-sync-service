package com.example.reachsync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncRequest {
    private CrmType crmType;
    private OperationType operationType;
    private InternalRecord internalRecord;
}
