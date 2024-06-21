package com.semicolon.africa.electionManagementSystem;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.UpdateVoterResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.exceptions.VoterAlreadyExistException;
import com.semicolon.africa.electionManagementSystem.services.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
        assertThrows(VoterAlreadyExistException.class, ()-> voterService.registerVoter(registrationRequest));

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
    @Test
    public void updateVoterBioTest() throws JsonPointerException {
        String lastName = voterService.findVoterBy(200L).getLastName();
        assertThat(lastName).isNotEqualTo("Samson");
        List<JsonPatchOperation> operationList = List.of(new ReplaceOperation(new JsonPointer("/lastName"), new TextNode("Samson")));
        JsonPatch updated = new JsonPatch(operationList);
        UpdateVoterResponse updateResponse = voterService.updateVoterDetails(200L, updated);
        assertThat(updateResponse).isNotNull();
        lastName = voterService.findVoterBy(200L).getLastName();
        assertThat(lastName).isNotEqualTo("Samson");

    }

}
