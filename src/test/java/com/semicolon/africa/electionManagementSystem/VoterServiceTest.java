package com.semicolon.africa.electionManagementSystem;

import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.VoterAlreadyExistException;
import com.semicolon.africa.electionManagementSystem.services.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql({"/database/data.sql"})
public class VoterServiceTest {
    @Autowired
    VoterService voterService;

    @Test
    public void registerVoterTest() {
            VoterRegistrationRequest registrationRequest = new VoterRegistrationRequest();
            registrationRequest.setFirstName("John");
            registrationRequest.setLastName("Doe");
            registrationRequest.setEmail("john@doe.com");
            registrationRequest.setDateOfBirth("01/01/1990");
            registrationRequest.setStateOfOrigin("Lagos");
            registrationRequest.setPassword("password");
            VoterRegistrationResponse response = voterService.registerVoter(registrationRequest);
            assertEquals(5, voterService.getNumberOfVoters().size());

    }

    @Test
    public void registerExistingVoter_throwsExceptionTest() {
        VoterRegistrationRequest registrationRequest = new VoterRegistrationRequest();
        registrationRequest.setFirstName("John");
        registrationRequest.setLastName("Doe");
        registrationRequest.setEmail("john@doe.com");
        registrationRequest.setDateOfBirth("01/01/1990");
        registrationRequest.setDateOfBirth("Lagos");
        registrationRequest.setPassword("password");
        voterService.registerVoter(registrationRequest);
        assertEquals(5, voterService.getNumberOfVoters().size());
        assertThrows(VoterAlreadyExistException.class, ()->voterService.registerVoter(registrationRequest));

    }

}
