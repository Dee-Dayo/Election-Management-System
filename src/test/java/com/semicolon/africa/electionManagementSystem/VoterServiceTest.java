package com.semicolon.africa.electionManagementSystem;

import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.services.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoterServiceTest {
    @Autowired
    VoterService voterService;

    @Test
    public void registerVoter() {
        VoterRegistrationRequest registrationRequest = new VoterRegistrationRequest();
        registrationRequest.setFirstName("John");
        registrationRequest.setLastName("Doe");
        registrationRequest.setEmail("john@doe.com");
        registrationRequest.setDateOfBirth("01/01/1990");
        registrationRequest.setDateOfBirth("Lagos");
        registrationRequest.setPassword("password");
        VoterRegistrationResponse response = voterService.registerVoter(registrationRequest);
        assertEquals(5, voterService.getNumberOfVoters());
    }
}
