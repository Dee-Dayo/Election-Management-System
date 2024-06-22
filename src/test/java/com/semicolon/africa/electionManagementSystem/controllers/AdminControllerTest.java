package com.semicolon.africa.electionManagementSystem.controllers;

import com.semicolon.africa.electionManagementSystem.services.JwtService;
import com.semicolon.africa.electionManagementSystem.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/database/data.sql"})
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private String adminJwtToken;

    @BeforeEach
    public void setup() {
        UserDetails adminUserDetails = userDetailsService.loadUserByUsername("admin@example.com");
        adminJwtToken = "Bearer " + jwtService.generateToken(adminUserDetails);
    }

    @Test
    public void adminCanScheduleElectionTest() throws Exception {
        String requestBody = "{ \"electionTitle\": \"Test Election\", \"startDate\": \"2024-06-21T00:00:00\", \"endDate\": \"2024-06-22T00:00:00\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", adminJwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.electionId").exists())
                .andExpect(jsonPath("$.electionTitle").value("Test Election"));
    }
}
