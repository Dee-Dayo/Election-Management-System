package com.semicolon.africa.electionManagementSystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.africa.electionManagementSystem.dtos.requests.*;
import com.semicolon.africa.electionManagementSystem.dtos.responses.*;
import com.semicolon.africa.electionManagementSystem.exceptions.*;
import com.semicolon.africa.electionManagementSystem.models.Admin;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.models.Role;
import com.semicolon.africa.electionManagementSystem.repositories.AdminRepository;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import com.semicolon.africa.electionManagementSystem.repositories.ElectionRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.semicolon.africa.electionManagementSystem.models.Role.ADMIN;
import static com.semicolon.africa.electionManagementSystem.models.Schedule.CANCELLED;
import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;
import static com.semicolon.africa.electionManagementSystem.utils.validations.Validations.verifyEmailAddress;

@Service
public class ElectionManagementService implements AdminService{

    private ElectionRepository electionRepository;
    private AdminRepository adminRepository;
    private CandidateService candidateService;

    @Autowired
    private ModelMapper modelMapper;
    //TODO What this service can do
        //Register Admin
        //Login Admin
        //Logout Admin
        //Schedule Election
        //View Election Results
        //Get Election Method- Returns Election
        //Cancel Election
        //Get All Elections Scheduled and Cancelled by an Admin
        //Start Election


    @Autowired
    public void setElectionRepository(@Lazy ElectionRepository electionRepository){
        this.electionRepository = electionRepository;
    }
    @Autowired
    public void setAdminRepository(@Lazy AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
    @Autowired
    public void setCandidateService(@Lazy CandidateService candidateService){
        this.candidateService = candidateService;
    }
    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest request) {
        Admin admin = getAdmin(request.getAdminId());
        ModelMapper mapper = new ModelMapper();
        Election election = mapper.map(request, Election.class);
        election.setAdminScheduler(admin.getAdminId());
        election.setSchedule(SCHEDULED);
        Election savedElection = electionRepository.save(election);
        ScheduleElectionResponse response = mapper.map(savedElection, ScheduleElectionResponse.class);
        response.setMessage(String.format("Election successfully Scheduled by %s %s", admin.getLastName(), admin.getFirstName()));

        return response;
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
        Admin admin = getAdmin(request.getAdminId());
        foundElection.setAdminCanceller(admin.getAdminId());
        foundElection.setSchedule(CANCELLED);
        CancelElectionResponse response = new ModelMapper().map(foundElection, CancelElectionResponse.class);
        response.setCancellerId(admin.getAdminId());
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setReason(request.getReason());
        response.setMessage("Election Cancelled Successfully");
        return response;
    }

    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest request) {
//        verifyEmailAddress(request.getEmail());
        Admin admin = modelMapper.map(request, Admin.class);
        admin.setRole(ADMIN);
        admin = adminRepository.save(admin);
        RegisterAdminResponse response = modelMapper.map(admin, RegisterAdminResponse.class);
        response.setMesssage("Admin registered successfully");
        return response;
    }

    @Override
    public Admin getAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new UserNotFoundException(String.format("Admin id: %d not found",adminId)));
        return admin;
    }

    @Override
    public RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request) {
        Election election = findElectionBy(request.getElectionId());
        Admin reScheduler = getAdmin(request.getReSchedulerId());
        election.setReScheduledBy(reScheduler.getAdminId());
        if (request.getUpdateStartTime() != null)election.setStartDate(LocalDateTime.parse(request.getUpdateStartTime()));
        if (request.getUpdateEndTime() != null)election.setEndDate(LocalDateTime.parse(request.getUpdateEndTime()));
        election = electionRepository.save(election);
        ModelMapper mapper =  configureMapper(modelMapper);
        RescheduleElectionResponse response = mapper.map(election,RescheduleElectionResponse.class);
        response.setMessage("Election Rescheduled Successfully");

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

    private ModelMapper configureMapper(ModelMapper modelMapper){
        modelMapper.typeMap(Election.class,RescheduleElectionResponse.class).addMappings(
                mapper -> {
                    mapper.map(Election::getElectionId,RescheduleElectionResponse::setElectionId);
                    mapper.map(Election::getStartDate, RescheduleElectionResponse::setNewStartDateTime);
                    mapper.map(Election::getEndDate, RescheduleElectionResponse::setNewEndDateTime);
                    mapper.map(Election::getTitle, RescheduleElectionResponse::setElectionTitle);
                    mapper.map(Election::getCategory, RescheduleElectionResponse::setElectionCategory);
                    mapper.map(src -> getAdmin(src.getAdminScheduler()).getLastName() +" "+ getAdmin(src.getAdminScheduler()).getFirstName(), RescheduleElectionResponse::setScheduledBy);
                    mapper.map(src -> getAdmin(src.getReScheduledBy()).getFirstName() +" "+ getAdmin(src.getReScheduledBy()).getLastName(),RescheduleElectionResponse::setReSchedulerName);
                }
        );
        return modelMapper;
    }


}
