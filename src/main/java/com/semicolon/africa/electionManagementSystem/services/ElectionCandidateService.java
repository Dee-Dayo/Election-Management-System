package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.CandidateNotFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.semicolon.africa.electionManagementSystem.utils.validations.Validations.validateCandidate;
import static com.semicolon.africa.electionManagementSystem.utils.validations.Validations.verifyEmailAddress;

@Service
public class ElectionCandidateService implements CandidateService {
    @Autowired
    private CandidateRepository candidates;

    @Autowired
    private ModelMapper modelMapper;

    private final VoteService voteService;

    public ElectionCandidateService(VoteService voteService){
        this.voteService = voteService;
    }

    @Override
    public RegisterCandidateResponse registerCandidateWith(RegisterCandidateRequest request) {
        candidates.findAll().forEach(candidate -> validateCandidate(request, candidate));
        verifyEmailAddress(request.getEmail());
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
    public List<Candidate> findAllElectionCandidates(Long electionId) {
        return null;
    }
}
