package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.*;
import com.semicolon.africa.electionManagementSystem.dtos.responses.*;
import com.semicolon.africa.electionManagementSystem.models.Admin;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;

import java.util.List;


public interface AdminService {
    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest request);

    ElectionScheduledResponse getElectionSchedule(Long electionId);


    Election findElectionBy(Long electionId);


    List<Candidate> getElectionCandidates(Long electionId);

    CancelElectionResponse cancelElection(CancelElectionRequest request);

    RegisterAdminResponse registerAdmin(RegisterAdminRequest request);

    Admin getAdmin(Long adminId);

    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request);
}
