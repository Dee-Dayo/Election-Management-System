package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;

public interface CandidateService {

    RegisterCandidateResponse registerCandidateWith(RegisterCandidateRequest request);

    Long getNumberOfCandidates();
}
