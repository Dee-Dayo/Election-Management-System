package com.semicolon.africa.electionManagementSystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
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


        @Test
        public void testThatCandidateCanRegister() throws JsonProcessingException {
            try {
                String requestBody = "{\"firstName\": \"jummy\", \"lastName\": \"jumoke\" ," +
                        " \"email\": \"ajibolaphilip10@gmail.com\", \"username\": \"jummyjhay\", \"password\": \"jummy1\",\"role\": \"CANDIDATE\"}" ;

<<<<<<< HEAD
                mockMvc.perform(MockMvcRequestBuilders.post("/api/candidate/register")
=======
                mockMvc.perform(MockMvcRequestBuilders.post("/candidate/register")
>>>>>>> 23dcecdc50216c6ebb0e470ff6c0c26b6bbc4669
                                .contentType(MediaType.APPLICATION_JSON)
                               .content(requestBody))
                               .andExpect(status().isCreated())
                                .andDo(print());
            }catch (Exception exception){
                assertThat(exception).isNull();
            }


        }


    @Test
    public void testThatElectionResultCanBeViewed() throws Exception {

        ShowElectionResultResponse response = new ShowElectionResultResponse();
        response.setElectionId(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/candidate/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(response)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }






}