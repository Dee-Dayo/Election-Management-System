package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ElectionScheduledResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionNotFoundException;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.models.Schedule;
import com.semicolon.africa.electionManagementSystem.repositories.AdminRepository;
import com.semicolon.africa.electionManagementSystem.repositories.ElectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;

@Service
@AllArgsConstructor
public class ElectionManagementService implements AdminService{

    private final ElectionRepository electionRepository;
    //TODO What this service can do
        //Register Admin
        //Login Admin
        //Logout Admin
        //Schedule Election
        //Register Candidate
        //View Election Results
        //Calculate Results for Election
    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest request) {
        ModelMapper mapper = new ModelMapper();
        Election election = mapper.map(request, Election.class);
        election.setSchedule(SCHEDULED);
        Election savedElection = electionRepository.save(election);

        return mapper.map(savedElection, ScheduleElectionResponse.class);
    }

    @Override
    public ElectionScheduledResponse getElectionSchedule(Long electionId) {
        Election foundElection = electionRepository.getElectionByElectionId(electionId);
        if (foundElection == null) {
            throw new ElectionNotFoundException(String.format("Election %d not found",electionId));
        }
        return new ModelMapper().map(foundElection,ElectionScheduledResponse.class);
    }


}
