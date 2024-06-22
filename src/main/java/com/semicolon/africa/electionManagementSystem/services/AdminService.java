package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.CancelElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.CancelElectionResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ElectionScheduledResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;

import java.util.List;


public interface AdminService {
    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest request);

    ElectionScheduledResponse getElectionSchedule(Long electionId);

    RegisterCandidateResponse registerCandidate(RegisterCandidateRequest request);

    Election findElectionBy(Long electionId);


    List<Candidate> getElectionCandidates(Long electionId);

    CancelElectionResponse cancelElection(CancelElectionRequest request);
}
