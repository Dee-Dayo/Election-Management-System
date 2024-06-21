package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.CandidateNotFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionCandidateService implements CandidateService {
    @Autowired
    private CandidateRepository candidates;
    @Autowired
    private ModelMapper modelMapper;
    private final VoteService voteService;

    public ElectionCandidateService(VoteService voteService) {
        this.voteService = voteService;
    }

    @Override
    public RegisterCandidateResponse registerCandidateWith(RegisterCandidateRequest request) {
        candidates
                .findAll()
                .forEach(candidate -> {if (candidate.getPositionContested()
                        .equals(request.getPositionContested()) && candidate.getPartyAffiliation()
                        .equals(request.getPartyAffiliation())) {
                    throw new NoVoterFoundException("candidate under " + request.getPartyAffiliation()+ " exists for "+request.getPositionContested());
                }});

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
        return candidates.findById(candidateId).orElseThrow(()-> new CandidateNotFoundException("candidate not found"));
    }

    @Override
    public Object viewElectionResultFor(long electionId) {
        return voteService.showResult(electionId);
    }


}
