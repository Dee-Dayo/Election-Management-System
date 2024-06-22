package com.semicolon.africa.electionManagementSystem.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.semicolon.africa.electionManagementSystem.dtos.requests.UpdateVoterRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.UpdateVoterResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.exceptions.VoterAlreadyExistException;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@AllArgsConstructor
@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;

    @Override
    public VoterRegistrationResponse registerVoter(VoterRegistrationRequest registrationRequest) {

        LocalDate dateOfBirth = LocalDate.parse(registrationRequest.getDateOfBirth());
        if(Period.between(dateOfBirth,LocalDate.now()).getYears() < 18) throw new ElectionManagementSystemException("not eligible to vote");


        Voter voter = new Voter();
        Voter registeredVoter = voterRepository.findByEmail(registrationRequest.getEmail());
        if (registeredVoter != null) throw new VoterAlreadyExistException(voter.getEmail() + " already exist");
        voter = modelMapper.map(registrationRequest, Voter.class);
        voter = voterRepository.save(voter);

        VoterRegistrationResponse registrationResponse = modelMapper.map(voter, VoterRegistrationResponse.class);
        registrationResponse.setMessage("Voter Registered Successfully");
        return registrationResponse;
    }

    @Override
    public List<Voter> getNumberOfVoters() {
        return voterRepository.findAll();
    }

    @Override
    public Voter findVoterBy(Long voterId) {
        return null;
    }

    @Override
    public UpdateVoterResponse updateVoterDetails(Long voterId, JsonPatch jsonPatch) {
        try {
            Voter registeredVoter = findVoterBy(voterId);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.convertValue(registeredVoter, JsonNode.class);
            node = jsonPatch.apply(node);
            registeredVoter = mapper.convertValue(node, Voter.class);
            registeredVoter = voterRepository.save(registeredVoter);
            return modelMapper.map(registeredVoter, UpdateVoterResponse.class);
        } catch (JsonPatchException e) {
            throw new ElectionManagementSystemException("Incorrect Voter Id");
        }
    }
}
