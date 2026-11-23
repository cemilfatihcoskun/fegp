package com.fatih.fegp.citizen.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CitizenControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAndRetrieveCitizen() throws Exception {
	String requestBody = """
		{
                    "nationalId": 10000000146,
                    "name": "Fatih"
                }
		""";

	mockMvc.perform(post("/api/citizens").contentType(MediaType.APPLICATION_JSON).content(requestBody))
		.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Fatih"));
    }

    @Test
    void shouldReturn405WhenCitizenNotFound() throws Exception {
	mockMvc.perform(get("/api/citizens/99999999999")).andExpect(status().isNotFound());
    }

}
