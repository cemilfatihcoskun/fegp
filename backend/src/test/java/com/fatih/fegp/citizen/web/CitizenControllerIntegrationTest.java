package com.fatih.fegp.citizen.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CitizenControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndRetrieveCitizen() throws Exception {
        Map<String, Object> payload = new LinkedHashMap<String, Object>();
        payload.put("citizenId", "10000000146");
        payload.put("name", "Fatih");
        payload.put("isActive", true);

        String requestBody = objectMapper.writeValueAsString(payload);

        mockMvc.perform(post("/api/citizens").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Fatih"));
    }

    @Test
    void shouldReturn406WhenCitizenNotFound() throws Exception {
        mockMvc.perform(get("/api/citizens/100000000000")).andExpect(status().isNotFound());
    }

}
