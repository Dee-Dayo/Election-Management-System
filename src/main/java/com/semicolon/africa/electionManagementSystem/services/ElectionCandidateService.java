package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.DeleteCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.DeleteCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.CandidateNotFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;
import static com.semicolon.africa.electionManagementSystem.utils.validations.Validations.*;

@Service
public class ElectionCandidateService implements CandidateService {
    @Autowired
    private CandidateRepository candidates;

    @Autowired
    private ModelMapper modelMapper;

    private VoteService voteService;
    private AdminService adminService;
    private ElectionManagementService electionService;

    @Autowired
    public void setVoteService(@Lazy VoteService voteService){
        this.voteService = voteService;
    }
    @Autowired
    public void setAdminService(@Lazy AdminService adminService){
        this.adminService = adminService;
    }
    @Autowired
    public void setElectionService(@Lazy ElectionManagementService electionService){
        this.electionService = electionService;
    }


    @Override
    public RegisterCandidateResponse registerCandidateWith(RegisterCandidateRequest request) {
        Election election = electionService.findElectionBy(request.getElectionId());
        validateElectionScheduleAndCategory(election,request);
        verifyEmailAddress(request.getEmail());
        candidates.findAll().forEach(candidate -> validateCandidate(request, candidate));
        Candidate candidate = modelMapper.map(request, Candidate.class);
        candidates.save(candidate);
        RegisterCandidateResponse response = modelMapper.map(candidate, RegisterCandidateResponse.class);
        response.setMessage("Candidate Successfully registered");
        return response;
    }



    @Override
    public Long getNumberOfCandidates() {
        return candidates.count();
    }

    @Override
    public Candidate findCandidateBy(Long candidateId) {
        return candidates.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException("candidate not found"));
    }

    @Override
    public ShowElectionResultResponse viewElectionResultFor(long electionId) {
        return voteService.showResult(electionId);
    }

    @Override
    public DeleteCandidateResponse deleteCandidate(DeleteCandidateRequest request) {
        Candidate candidate = findCandidateBy(request.getCandidateId());
        candidates.delete(candidate);
        DeleteCandidateResponse response = modelMapper.map(candidate, DeleteCandidateResponse.class);
        response.setMessage("candidate deleted");
        return response;
    }

    @Override
    public List<Candidate> findAllElectionCandidates(Long electionId) {
        electionService.findElectionBy(electionId);
        return candidates.findByElectionId(electionId);
    }


}
