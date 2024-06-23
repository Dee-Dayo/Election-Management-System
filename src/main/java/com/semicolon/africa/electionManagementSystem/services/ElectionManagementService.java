package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.CancelElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.CancelElectionResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ElectionScheduledResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.DeniedAccessException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionNotFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.NoCandidateRegisteredException;
import com.semicolon.africa.electionManagementSystem.exceptions.UserNotFoundException;
import com.semicolon.africa.electionManagementSystem.models.Admin;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.repositories.AdminRepository;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import com.semicolon.africa.electionManagementSystem.repositories.ElectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.electionManagementSystem.models.Schedule.CANCELLED;
import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;

@Service
//@AllArgsConstructor
public class ElectionManagementService implements AdminService{

    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;
    private final AdminRepository adminRepository;
    private final CandidateService candidateService;
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
        //Cancel Election
        //Get All Elections Scheduled and Cancelled by an Admin

    public ElectionManagementService(ElectionRepository electionRepository, CandidateRepository candidateRepository, AdminRepository adminRepository, CandidateService candidateService){
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
        this.adminRepository = adminRepository;
        this.candidateService = candidateService;
    }
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
//        Election election = findElectionBy(request.getElectionId());
//        if (election.getCategory() != request.getPositionContested())throw new DeniedAccessException("Candidate cannot contest in this election category");
//        ModelMapper modelMapper = configure(new ModelMapper());
//        Candidate candidate = modelMapper.map(request, Candidate.class);
//        Candidate savedCandidate = candidateRepository.save(candidate);
//        RegisterCandidateResponse response = modelMapper.map(savedCandidate, RegisterCandidateResponse.class);
//        response.setMessage("Candidate registered successfully");
            RegisterCandidateResponse response = candidateService.registerCandidateWith(request);
        return response;
    }

    @Override
    public Election findElectionBy(Long electionId) {
        Election election = electionRepository.getElectionByElectionId(electionId);
        if (election == null) {
            throw new ElectionNotFoundException(String.format("No Scheduled Election with id: %d", electionId));
        }
        return election;
    }


    @Override
    public List<Candidate> getElectionCandidates(Long electionId) {
        List<Candidate> candidates = candidateService.findAllElectionCandidates(
                electionId).stream()
                .filter(candidate -> candidate.getElection().getElectionId().equals(electionId)).toList();
        if (candidates.isEmpty())throw new NoCandidateRegisteredException(String.format("No Candidates Registered for Election id: %d",electionId));
        return candidates;
    }

    @Override
    public CancelElectionResponse cancelElection(CancelElectionRequest request) {
        Election foundElection = findElectionBy(request.getElectionId());
        Admin admin = adminRepository.findById(request.getAdminId()).orElseThrow(() -> new UserNotFoundException(String.format("Admin id: %d not found",request.getAdminId())));
        foundElection.setSchedule(CANCELLED);
        CancelElectionResponse response = new ModelMapper().map(foundElection, CancelElectionResponse.class);
        response.setCancellerId(admin.getAdminId());
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setReason(request.getReason());
        response.setMessage("Election Cancelled Successfully");
        return response;
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
