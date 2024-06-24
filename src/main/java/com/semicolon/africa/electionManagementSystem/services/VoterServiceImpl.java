package com.semicolon.africa.electionManagementSystem.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.semicolon.africa.electionManagementSystem.dtos.requests.UpdateVoterRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.UpdateVoterResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.models.Role;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static com.semicolon.africa.electionManagementSystem.utils.validations.Validations.validateFor;
import static com.semicolon.africa.electionManagementSystem.utils.validations.Validations.verifyEmailAddress;


@Service
public class VoterServiceImpl implements VoterService {
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private ModelMapper modelMapper;
    private  VoterService voterService;

    private VoteService voteService;
    @Autowired
    private AdminService adminService;

    @Autowired
    public void setVoteService(@Lazy VoteService voteService) {
        this.voteService = voteService;
    }

    @Override
    public VoterRegistrationResponse registerVoter(VoterRegistrationRequest registrationRequest) {

        LocalDate dateOfBirth = LocalDate.parse(registrationRequest.getDateOfBirth());
        if(Period.between(dateOfBirth,LocalDate.now()).getYears() < 18) throw new ElectionManagementSystemException("Not eligible to vote");

        Voter voter = new Voter();
        Voter registeredVoter = voterRepository.findByEmail(registrationRequest.getEmail());
        validateFor(registeredVoter);
        verifyEmailAddress(registrationRequest.getEmail());
        voter = modelMapper.map(registrationRequest, Voter.class);
        voter.setRole(Role.VOTER);
        voter = voterRepository.save(voter);

        VoterRegistrationResponse registrationResponse = modelMapper.map(voter, VoterRegistrationResponse.class);
        registrationResponse.setMessage("Voter Registered Successfully");
        return registrationResponse;
    }

    @Override
    public Long getNumberOfVoters() {
        return voterRepository.count();
    }

    @Override
    public Voter findVoterBy(Long voterId) {
        return voterRepository.findById(voterId).orElseThrow(() -> new ElectionManagementSystemException("Voter does not exist"));
    }



    @Override
    public UpdateVoterResponse updateVoterBioData(UpdateVoterRequest updateRequest) {
        Voter registeredVoter = voterRepository.findById(updateRequest.getVoterId()).
                orElseThrow(() -> new ElectionManagementSystemException("Voter does not exist"));
        registeredVoter = modelMapper.map(updateRequest, Voter.class);
        registeredVoter.setRole(Role.VOTER);
        registeredVoter = voterRepository.save(registeredVoter);

        UpdateVoterResponse response = modelMapper.map(registeredVoter, UpdateVoterResponse.class);
        response.setMessage("Voter Updated Successfully");
        return response;
    }


//    @Override
//    public UpdateVoterResponse updateVoterBioData(Long voterId, JsonPatch jsonPatch) {
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

    @Override
    public ShowElectionResultResponse viewElectionResult(Long electionId) {
        return voteService.showResult(electionId);
    }

    @Override
    public List<Voter> findAllRegisteredVoters(Long electionId) {
        adminService.findElectionBy(electionId);
        return voterRepository.findAll();
    }


}
