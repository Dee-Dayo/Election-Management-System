package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ElectionScheduledResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import org.springframework.stereotype.Service;


public interface AdminService {
    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest request);

    ElectionScheduledResponse getElectionSchedule(int electionId);
}
