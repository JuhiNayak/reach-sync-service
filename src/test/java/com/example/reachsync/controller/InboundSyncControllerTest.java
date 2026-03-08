package com.example.reachsync.controller;

import com.example.reachsync.service.InboundSyncService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InboundSyncControllerTest {

    @Test
    void testShouldReceiveWebhookEvent() throws Exception {

        InboundSyncService service = Mockito.mock(InboundSyncService.class);

        InboundSyncController controller = new InboundSyncController(service);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        String webHookJson = """
                {
                    "crmType": "SALESFORCE",
                    "operationType": "CREATE",
                    "payload": {
                        "First_Name_c": "John"
                    }
                }
                """;

        mockMvc.perform(post("/crm/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(webHookJson))
                .andExpect(status().isOk());
    }
}
