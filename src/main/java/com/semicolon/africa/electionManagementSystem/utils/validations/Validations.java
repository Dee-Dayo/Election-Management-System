package com.semicolon.africa.electionManagementSystem.utils.validations;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.exceptions.NoVoterFoundException;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import org.modelmapper.ModelMapper;

import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;

public class Validations {

    public static void verifyEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String emailRegex2 = "([a-z]\\.)?[a-z]+@(semicolon|enum|learnspace|native.semicolon).africa";

        if (!email.matches(emailRegex) && !email.matches(emailRegex2))throw new ElectionManagementSystemException("Invalid email address");

    }
    public static void validateCandidate(RegisterCandidateRequest request, Candidate candidate) {
        if (candidate.getPositionContested().equals(request.getPositionContested())
                && candidate.getPartyAffiliation().equals(request.getPartyAffiliation())) {
            throw new NoVoterFoundException("candidate under " + request.getPartyAffiliation() + " exists for " + request.getPositionContested());
        }
    }

    public static void validateElectionScheduleAndCategory(Election election, RegisterCandidateRequest request) {
        if (election == null) throw new ElectionManagementSystemException("Election not found");
        if(election.getSchedule()!= SCHEDULED)
            throw new ElectionManagementSystemException("Can not register candidate for this election");
        if(election.getCategory() != request.getPositionContested())
            throw new ElectionManagementSystemException("Change the intended candidate category to "+election.getCategory());
    }

    public static ModelMapper configure(ModelMapper modelMapper) {
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

}
