package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.UpdateVoterRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.models.Role;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if(Period.between(dateOfBirth,LocalDate.now()).getYears() < 18) throw new ElectionManagementSystemException("Not eligible to vote");


        Voter voter = new Voter();
        Voter registeredVoter = voterRepository.findByEmail(registrationRequest.getEmail());
        if (registeredVoter != null) throw new ElectionManagementSystemException(voter.getEmail() + " already exist");
        voter = modelMapper.map(registrationRequest, Voter.class);
        voter.setRole(Role.VOTER);
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
       //return voterRepository.findById(voterId).orElseThrow(() -> new ElectionManagementSystemException("Voter does not exist"));
    }

//    @Override
//    public VoterRegistrationResponse updateVoterBioData(UpdateVoterRequest updateRequest) {
//        Voter registeredVoter = voterRepository.findById(updateRequest.getVoterId()).
//                orElseThrow(() -> new ElectionManagementSystemException("Voter does not exist"));
//        registeredVoter = modelMapper.map(updateRequest, Voter.class);
//        registeredVoter.setRole(Role.VOTER);
//        registeredVoter = voterRepository.save(registeredVoter);
//
//        VoterRegistrationResponse response = modelMapper.map(registeredVoter, VoterRegistrationResponse.class);
//        response.setMessage("Voter Updated Successfully");
//        return response;
//    }


//    @Override
//    public UpdateVoterResponse updateVoterDetails(Long voterId, JsonPatch jsonPatch) {
//        try {
//            Voter registeredVoter = findVoterBy(voterId);
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode node = mapper.convertValue(registeredVoter, JsonNode.class);
//            node = jsonPatch.apply(node);
//            registeredVoter = mapper.convertValue(node, Voter.class);
//            registeredVoter = voterRepository.save(registeredVoter);
//            return modelMapper.map(registeredVoter, UpdateVoterResponse.class);
//        } catch (JsonPatchException e) {
//            throw new ElectionManagementSystemException("Incorrect Voter Id");
//        }
//    }


}
