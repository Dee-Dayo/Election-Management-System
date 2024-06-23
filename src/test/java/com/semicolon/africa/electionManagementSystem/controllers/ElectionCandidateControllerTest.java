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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
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

                mockMvc.perform(MockMvcRequestBuilders.post("/candidate/register")
                                .contentType(MediaType.APPLICATION_JSON)
                               .content(requestBody))
                               .andExpect(status().isCreated())
                                .andDo(print());
            }catch (Exception exception){
                assertThat(exception).isNotNull();
            }


        }


    @Test
    public void testThatElectionResultCanBeViewed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(" /candidate/200")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }






}