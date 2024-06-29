package com.semicolon.africa.electionManagementSystem.services;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.semicolon.africa.electionManagementSystem.dtos.requests.DeleteCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.DeleteCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionNotFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.VoteNotFoundException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static com.semicolon.africa.electionManagementSystem.models.Category.NATIONAL;
import static com.semicolon.africa.electionManagementSystem.models.Category.STATE;
import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class  CandidateServiceTest {
    private static final Logger log = LoggerFactory.getLogger(CandidateServiceTest.class);
    @Autowired
    CandidateService candidateService;
    @Autowired
    CandidateRepository candidateRepository;
    private RegisterCandidateRequest request;

    @BeforeEach
    public void setUp(){
        candidateRepository.deleteAll();
        request = new RegisterCandidateRequest();
    }

    @Test
    public void testRegisterCandidate_ListOfCandidatesIncreases() {
        registerCandidate();
    }

    @Test
    public void testRegisterCandidateForSameCategoryAndSameParty_listRemainsTheSame(){
        request.setFirstName("Femi");
        request.setLastName("Gbajabiamila");
        request.setEmail("femigba@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(PDP);
        request.setUsername("Fems");
        request.setElectionId(200L);
        request.setPositionContested(NATIONAL);
        candidateService.registerCandidateWith(request);
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(1L);

        RegisterCandidateRequest request2 = new RegisterCandidateRequest();
        request2.setFirstName("Atiku");
        request2.setLastName("Obi");
        request2.setEmail("jojofolani@gmail.com");
        request.setElectionId(200L);
        request2.setPassword("123456");
        request2.setPartyAffiliation(PDP);
        request2.setUsername("Fems");
        request2.setPositionContested(NATIONAL);
        assertThrows(NoVoterFoundException.class, ()-> candidateService.registerCandidateWith(request2));
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(1L);
    }

    private void registerCandidate() {
        request.setFirstName("Ahmed");
        request.setLastName("Tinubu");
        request.setEmail("victormsonter@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(LP);
        request.setUsername("Bat");
        request.setElectionId(200L);
        request.setPositionContested(NATIONAL);
        RegisterCandidateResponse response1 = candidateService.registerCandidateWith(request);
        assertThat(response1).isNotNull();
        assertThat(response1.getCandidateId()).isNotNull();
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(1L);
    }

    @Test
    public void viewResultTest(){
        var result = candidateService.viewElectionResultFor(200L);
        assertThat(result).isNotNull();
    }

    @Test
    public void registerCandidateWithInvalidEmail_throwsExceptionTest(){
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Ahmed");
        request.setLastName("Tinubu");
        request.setEmail("lukasgrahamgmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(PDP);
        request.setElectionId(200L);
        request.setUsername("Bat");
        request.setPositionContested(STATE);
        assertThrows(ElectionNotFoundException.class, ()-> candidateService.registerCandidateWith(request));
    }

    @Test
    public void deleteCandidate_candidateIsRemovedFromElectionTest(){
        Long adminId = 100L;
        Long electionId = 200L;
        Long candidateId = 101L;
        DeleteCandidateRequest request = new DeleteCandidateRequest();
        request.setElectionId(electionId);
        request.setCandidateId(candidateId);
        request.setAdminId(adminId);
        DeleteCandidateResponse response = candidateService.deleteCandidate(request);
        assertThat(response.getMessage().contains("candidate deleted"));

    }

    @Test
    public void findAllCandidatesForAnElectionTest(){
        Long electionId = 200L;
        List<Candidate> candidates = candidateService.findAllElectionCandidates(electionId);
        assertThat(candidates).hasSize(3);
    }

    @Test
    public void updateCandidate_candidateIsUpdatedTest() throws JsonPointerException {
        request.setFirstName("Ahmed");
        request.setLastName("Tinubu");
        request.setEmail("victormsonter@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(LP);
        request.setElectionId(200L);
        request.setUsername("Bat");
        request.setPositionContested(NATIONAL);
        RegisterCandidateResponse response1 = candidateService.registerCandidateWith(request);
        String firstName = candidateService.findCandidateBy(response1.getCandidateId()).getFirstName();
        assertThat(firstName).isNotEqualTo("new name");
        List<JsonPatchOperation> operations = List.of(new ReplaceOperation(new JsonPointer("/firstName"), new TextNode("new name")));
        JsonPatch request1 = new JsonPatch(operations);
        UpdateCandidateResponse response = candidateService.updateWith(response1.getCandidateId(), request1);
        assertThat(response).isNotNull();
        firstName = candidateService.findCandidateBy(response1.getCandidateId()).getFirstName();
        assertThat(firstName).isEqualTo("new name");
    }


}
