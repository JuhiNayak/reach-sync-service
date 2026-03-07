package com.example.reachsync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalRecord {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
