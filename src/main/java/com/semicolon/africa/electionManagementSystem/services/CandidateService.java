package com.semicolon.africa.electionManagementSystem.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.semicolon.africa.electionManagementSystem.dtos.requests.DeleteCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.DeleteCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {

    RegisterCandidateResponse registerCandidateWith(RegisterCandidateRequest request);

    Long getNumberOfCandidates();

    Candidate findCandidateBy(Long candidateId);

    ShowElectionResultResponse viewElectionResultFor(long electionId);

    DeleteCandidateResponse deleteCandidate(DeleteCandidateRequest request);

    List<Candidate> findAllElectionCandidates(Long electionId);

    UpdateCandidateResponse updateWith(Long candidateId, JsonPatch request);
}
