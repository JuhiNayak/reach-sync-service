package com.example.reachsync.controller;

import com.example.reachsync.model.SyncResult;
import com.example.reachsync.service.SyncService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SyncControllerTest {

    @Test
    void testShouldReturnSuccessForValidRequest() throws Exception {
        SyncService syncService = Mockito.mock(SyncService.class);

        Mockito.when(syncService.syncRecord(Mockito.any())).thenReturn(new SyncResult(true, "success"));

        SyncController syncController = new SyncController(syncService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(syncController).build();

        String requestJson = """
                {
                    "crmType": "SALESFORCE",
                    "operationType": "CREATE",
                    "internalRecord": {
                        "id": "123",
                        "firstName": "John",
                        "lastName": "Doe",
                        "email": "john@gmail.com"
                    }
                }
                """;

        mockMvc.perform(post("/sync")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andExpect(status().isOk());
    }
}
