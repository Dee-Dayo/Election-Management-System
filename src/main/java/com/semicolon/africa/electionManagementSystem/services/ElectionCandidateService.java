package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.repositories.CandidateRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElectionCandidateService implements CandidateService {
    private final CandidateRepository candidates;

    @Override
    public RegisterCandidateResponse registerCandidateWith(RegisterCandidateRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Candidate candidate = modelMapper.map(request, Candidate.class);
        //find election by id and put into the model
        candidates.save(candidate);
        RegisterCandidateResponse response = modelMapper.map(candidate, RegisterCandidateResponse.class);
        response.setMessage("Candidate Successfully registered");
        return response;
    }

    @Override
    public Long getNumberOfCandidates() {
        return candidates.count();
    }


}
