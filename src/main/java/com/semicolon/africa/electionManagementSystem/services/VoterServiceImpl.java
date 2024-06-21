package com.semicolon.africa.electionManagementSystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;

    @Override
    public VoterRegistrationResponse registerVoter(VoterRegistrationRequest registrationRequest) {

        Voter voter;
        voter = modelMapper.map(registrationRequest, Voter.class);
        Voter savedVoter = voterRepository.save(voter);

        VoterRegistrationResponse registrationResponse;
        registrationResponse = modelMapper.map(voter, VoterRegistrationResponse.class);
        registrationResponse.setMessage("Voter Registered Successfully");
        return registrationResponse;

    }

    @Override
    public List<Voter> getNumberOfVoters() {
        return voterRepository.findAll();
    }

    @Override
    public Voter findVoterBy(Long voterId) {
        return voterRepository.findById(voterId).orElseThrow(()-> new NoVoterFoundException("Can't Find This User"));
    }
}
