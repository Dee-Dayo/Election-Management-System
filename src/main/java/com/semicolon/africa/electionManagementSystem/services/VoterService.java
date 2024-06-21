package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoterService {
    VoterRegistrationResponse registerVoter(VoterRegistrationRequest registrationRequest);

    List<Voter> getNumberOfVoters();

    Voter findVoterBy(Long voterId);

}
