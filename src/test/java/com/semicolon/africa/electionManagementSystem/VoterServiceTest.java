package com.semicolon.africa.electionManagementSystem;

import com.semicolon.africa.electionManagementSystem.dtos.requests.UpdateVoterRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.services.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;
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
            registrationRequest.setDateOfBirth("1990-01-01");
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
        registrationRequest.setDateOfBirth("1990-01-01");
        registrationRequest.setStateOfOrigin("Lagos");
        registrationRequest.setPassword("password");
        voterService.registerVoter(registrationRequest);
        assertEquals(5, voterService.getNumberOfVoters().size());
        assertThrows(ElectionManagementSystemException.class, ()-> voterService.registerVoter(registrationRequest));

    }

    @Test
    public void registerVoter_throwsExceptionTest() {
        VoterRegistrationRequest registrationRequest = new VoterRegistrationRequest();
        registrationRequest.setFirstName("Grey");
        registrationRequest.setLastName("Doe");
        registrationRequest.setEmail("grey@doe.com");
        registrationRequest.setDateOfBirth("2016-01-01");
        registrationRequest.setStateOfOrigin("Lagos");
        registrationRequest.setPassword("password");
        assertThrows(ElectionManagementSystemException.class, ()-> voterService.registerVoter(registrationRequest));

    }
//    @Test
//    public void updateVoterBioTest() throws JsonPointerException {
//        String lastName = voterService.findVoterBy(200L).getLastName();
//        assertThat(lastName).isNotEqualTo("Samson");
//        List<JsonPatchOperation> operationList = List.of(new ReplaceOperation(new JsonPointer("/lastName"), new TextNode("Samson")));
//        JsonPatch updated = new JsonPatch(operationList);
//        UpdateVoterResponse updateResponse = voterService.updateVoterDetails(200L, updated);
//        assertThat(updateResponse).isNotNull();
//        lastName = voterService.findVoterBy(200L).getLastName();
//        assertThat(lastName).isNotEqualTo("Samson");
//
//    }

//    @Test
//    public void updateVoterBioDataTest() {
//        VoterRegistrationRequest registrationRequest = new VoterRegistrationRequest();
//        registrationRequest.setFirstName("Rebecca");
//        registrationRequest.setLastName("Nelson");
//        registrationRequest.setEmail("Darhyor2050@gmail.com");
//        registrationRequest.setDateOfBirth("1999-05-20");
//        registrationRequest.setStateOfOrigin("Lagos");
//        registrationRequest.setPassword("password");
//        VoterRegistrationResponse response = voterService.registerVoter(registrationRequest);
//        assertEquals(5, voterService.getNumberOfVoters().size());
//
//        UpdateVoterRequest updateRequest = new UpdateVoterRequest();
//        updateRequest.setVoterId(200L);
//        updateRequest.setFirstName(registrationRequest.getFirstName());
//        updateRequest.setLastName("Sams");
//        updateRequest.setPassword(registrationRequest.getPassword());
//        VoterRegistrationResponse newResponse = voterService.updateVoterBioData(updateRequest);
//        assertEquals(5, voterService.getNumberOfVoters().size());
//
//    }

}
