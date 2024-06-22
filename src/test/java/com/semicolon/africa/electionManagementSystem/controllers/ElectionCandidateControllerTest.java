package com.semicolon.africa.electionManagementSystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import com.semicolon.africa.electionManagementSystem.services.ElectionCandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/database/data.sql"})
class ElectionCandidateControllerTest {

        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ElectionCandidateService electionCandidateService;
        @Autowired
        private CandidateRepository candidateRepository;
        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void testThatCandidateCanRegister() throws JsonProcessingException {
            try {
                String requestBody = "{\"firstName\": \"jummy\", \"lastName\": \"jumoke\" ," +
                        " \"email\": \"josephfeyishetan@gmail.com\", \"username\": \"jummyjhay\", \"password\": \"jummy1\"}" ;

                mockMvc.perform(MockMvcRequestBuilders.post("/api/candidates/register")
                                .contentType(MediaType.APPLICATION_JSON)
                               .content(requestBody))
                               .andExpect(status().isCreated())
                                .andDo(print());
            }catch (Exception exception){
                assertThat(exception).isNull();
            }


        }



}