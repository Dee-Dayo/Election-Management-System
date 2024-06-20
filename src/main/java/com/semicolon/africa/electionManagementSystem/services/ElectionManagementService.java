package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ElectionScheduledResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.DeniedAccessException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionNotFoundException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.models.Schedule;
import com.semicolon.africa.electionManagementSystem.repositories.AdminRepository;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import com.semicolon.africa.electionManagementSystem.repositories.ElectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;

import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;

@Service
@AllArgsConstructor
public class ElectionManagementService implements AdminService{

    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;
    //TODO What this service can do
        //Register Admin
        //Login Admin
        //Logout Admin
        //Schedule Election
        //Register Candidate
        //View Election Results
        //Calculate Results for Election
        //Check if Election Category correlates with position contested
        //Get Election Method- Returns Election
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
            throw new ElectionNotFoundException(String.format("No Scheduled Election with id: %d",electionId));
        }
        return new ModelMapper().map(foundElection,ElectionScheduledResponse.class);
    }

    @Override
    public RegisterCandidateResponse registerCandidate(RegisterCandidateRequest request) {
        Election election = getElection(request.getElectionId());
        if (election.getCategory() != request.getPositionContested())throw new DeniedAccessException("Candidate cannot contest in this election category");
        ModelMapper modelMapper = configure(new ModelMapper());
        Candidate candidate = modelMapper.map(request, Candidate.class);
        Candidate savedCandidate = candidateRepository.save(candidate);
        RegisterCandidateResponse response = modelMapper.map(savedCandidate, RegisterCandidateResponse.class);
        response.setMessage("Candidate registered successfully");

        return response;
    }

    @Override
    public Election getElection(Long electionId) {
        Election election = electionRepository.getElectionByElectionId(electionId);
        if (election == null) {
            throw new ElectionNotFoundException(String.format("No Scheduled Election with id: %d", electionId));
        }
        return election;
    }

    private static ModelMapper configure(ModelMapper modelMapper) {
        modelMapper.typeMap(Candidate.class, RegisterCandidateResponse.class).addMappings(
                mapper -> {
                    mapper.map(Candidate::getCandidateId, RegisterCandidateResponse::setCandidateId);
                    mapper.map(Candidate::getFirstName, RegisterCandidateResponse::setFirstName);
                    mapper.map(Candidate::getLastName, RegisterCandidateResponse::setLastName);
                    mapper.map(Candidate::getPartyAffiliation, RegisterCandidateResponse::setPartyAffiliation);
                    mapper.map(src -> src.getElection().getStartDate(), RegisterCandidateResponse::setStartDate);
                    mapper.map(src -> src.getElection().getEndDate(), RegisterCandidateResponse::setEndDate);
                    mapper.map(src -> src.getElection().getCategory(), RegisterCandidateResponse::setCategory);
                    mapper.map(src -> src.getElection().getSchedule(), RegisterCandidateResponse::setSchedule);
                    mapper.map(src -> src.getElection().getTitle(), RegisterCandidateResponse::setElectionTitle);
                }
        );
        return modelMapper;
    }


}
