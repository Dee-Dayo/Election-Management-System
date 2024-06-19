package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.semicolon.africa.electionManagementSystem.models.Category.NATIONAL;
import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.APC;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CandidateServiceTest {
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
}
