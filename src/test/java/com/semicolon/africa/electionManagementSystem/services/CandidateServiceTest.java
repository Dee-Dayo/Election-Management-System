package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.semicolon.africa.electionManagementSystem.models.Category.NATIONAL;
import static com.semicolon.africa.electionManagementSystem.models.Category.STATE;
import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.APC;
import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.PDP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CandidateServiceTest {
    private static final Logger log = LoggerFactory.getLogger(CandidateServiceTest.class);
    @Autowired
    CandidateService candidateService;

    @Test
    public void testRegisterCandidate_ListOfCandidatesIncreases() {
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Ahmed");
        request.setLastName("Tinubu");
        request.setEmail("huchogrey73@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(APC);
        request.setElectionId(200L);
        request.setUsername("Bat");
        request.setPositionContested(NATIONAL);
        RegisterCandidateResponse response = candidateService.registerCandidateWith(request);
        assertThat(response).isNotNull();
        assertThat(response.getCandidateId()).isNotNull();
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(6L);
    }

    @Test
    public void testRegisterCandidateForSameCategoryAndSameParty_listRemainsTheSame(){
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Femi");
        request.setLastName("Gbajabiamila");
        request.setEmail("femigba@gmail.com");
        request.setPassword("123456");
        request.setPartyAffiliation(PDP);
        request.setElectionId(200L);
        request.setUsername("Fems");
        request.setPositionContested(STATE);
        candidateService.registerCandidateWith(request);
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(7L);
        log.info("Number of candidates: {}", candidateService.getNumberOfCandidates());

        RegisterCandidateRequest request2 = new RegisterCandidateRequest();
        request2.setFirstName("Atiku");
        request2.setLastName("Obi");
        request2.setEmail("jojofolani@gmail.com");
        request2.setPassword("123456");
        request2.setPartyAffiliation(PDP);
        request2.setElectionId(200L);
        request2.setUsername("Fems");
        request2.setPositionContested(STATE);
        assertThrows(NoVoterFoundException.class, ()-> candidateService.registerCandidateWith(request2));
        assertThat(candidateService.getNumberOfCandidates()).isEqualTo(7L);
        log.info("Number of candidates: {}", candidateService.getNumberOfCandidates());
    }

    @Test
    public void viewResultTest(){

    }
}