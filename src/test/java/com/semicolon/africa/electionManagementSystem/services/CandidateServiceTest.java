package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.DeleteCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.DeleteCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionNotFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static com.semicolon.africa.electionManagementSystem.models.Category.NATIONAL;
import static com.semicolon.africa.electionManagementSystem.models.Category.STATE;
import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.*;
import static com.semicolon.africa.electionManagementSystem.models.Role.CANDIDATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class  CandidateServiceTest {
    private static final Logger log = LoggerFactory.getLogger(CandidateServiceTest.class);
    @Autowired
    CandidateService candidateService;

    @Test
    public void testRegisterCandidate_ListOfCandidatesIncreases() {
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Ahmed");
        request.setLastName("Tinubu");
        request.setEmail("lukasgraham73@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(LP);
        request.setElectionId(200L);
        request.setAdminId(100L);
        request.setUsername("Bat");
        request.setPositionContested(NATIONAL);
        RegisterCandidateResponse response = candidateService.registerCandidateWith(request);
        assertThat(response).isNotNull();
        assertThat(response.getCandidateId()).isNotNull();
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(5L);
    }

    @Test
    public void testRegisterCandidateForSameCategoryAndSameParty_listRemainsTheSame(){
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Femi");
        request.setLastName("Gbajabiamila");
        request.setEmail("femigba@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(PDP);
        request.setAdminId(100L);
        request.setElectionId(200L);
        request.setUsername("Fems");
        request.setPositionContested(STATE);
        candidateService.registerCandidateWith(request);
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(6L);

        RegisterCandidateRequest request2 = new RegisterCandidateRequest();
        request2.setFirstName("Atiku");
        request2.setLastName("Obi");
        request2.setEmail("jojofolani@gmail.com");
        request2.setPassword("123456");
        request2.setPartyAffiliation(PDP);
        request2.setElectionId(200L);
        request.setAdminId(100L);
        request2.setUsername("Fems");
        request2.setPositionContested(STATE);
        assertThrows(NoVoterFoundException.class, ()-> candidateService.registerCandidateWith(request2));
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(6L);
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
        request.setAdminId(100L);
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


}
