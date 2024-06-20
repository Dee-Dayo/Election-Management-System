package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.VoterRegistrationRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterRegistrationResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.VoterAlreadyExistException;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;

    @Override
    public VoterRegistrationResponse registerVoter(VoterRegistrationRequest registrationRequest) {

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

<<<<<<< HEAD

=======
    @Override
    public Voter findVoterBy(Long voterId) {
        return null;
    }
>>>>>>> e92bd2cbacc5892350636f531552d8247f05b9fc
}
