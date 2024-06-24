package com.semicolon.africa.electionManagementSystem.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.semicolon.africa.electionManagementSystem.dtos.requests.UpdateVoterRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.UpdateVoterResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoterService {
    VoterRegistrationResponse registerVoter(VoterRegistrationRequest registrationRequest);

    Long getNumberOfVoters();

    Voter findVoterBy(Long voterId);

    UpdateVoterResponse updateVoterBioData(UpdateVoterRequest updateRequest);

//    UpdateVoterResponse updateVoterBioData(Long voterId, JsonPatch jsonPatch);

    ShowElectionResultResponse viewElectionResult(Long electionId);

    List<Voter> findAllRegisteredVoters(Long electionId);
}
