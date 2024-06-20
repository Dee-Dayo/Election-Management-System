<<<<<<< HEAD
//package com.semicolon.africa.electionManagementSystem.services;
//
//import com.semicolon.africa.electionManagementSystem.dtos.requests.CountCandidateVoteRequest;
//import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
//import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
//import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterResponse;
//import com.semicolon.africa.electionManagementSystem.models.*;
//import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//@AllArgsConstructor
//public class VoteServiceImpl implements VoteService {
//    private  CandidateService candidateService;
//    private VoterService voterService;
//    private AdminService adminService;
//    private ModelMapper mapper;
//    private VoterRepository votes;
//    @Override
//    public AddVoteResponse addVote(AddVoteRequest request) {
//        Candidate candidate = candidateService.findCandidateBy(request.getCandidateId());
//        Voter voter = voterService.findVoterBy(request.getVoterId());
//        Election election = adminService.findElectionBy(request.getElectionId());
//        Vote vote = new Vote();
//        vote.setVoter(voter);
//        vote.setCandidate(candidate);
//        vote.setElection(election);
//        VoterResponse voterResponse = mapper.map(voter,VoterResponse.class);
//        AddVoteResponse addVoteResponse =  mapper.map(vote,AddVoteResponse.class);
//        addVoteResponse.setVoterResponse(voterResponse);
//        return addVoteResponse;
//    }
//
//    @Override
//    public Long countElectionVote(Long electionId) {
//        return votes.countVote(electionId);
//    }
//
//    @Override
//    public Long countVoteForCandidate(CountCandidateVoteRequest request) {
//        return votes.countVoteForCandidate(request.getElectionId(),candidateService.findCandidateBy(request.getCandidateId()));
//    }
//
//    @Override
//    public ShowElectionResultResponse showResult(Long electionId) {
//        List<Candidate> candidates = adminService.getElectionCandidates(electionId);
//        Map<String, Long> results = new HashMap<>();
//        candidates.stream().forEach(candidate->{
//            results.put(candidate.getFirstName(),votes.countVoteForCandidate(electionId,candidate.getCandidateId()));
//        });
//
//        return new ShowElectionResultResponse(electionId, results);
//    }
//}
=======
package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountCandidateVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterResponse;
import com.semicolon.africa.electionManagementSystem.models.*;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {
    private  CandidateService candidateService;
    private VoterService voterService;
    private AdminService adminService;
    private ModelMapper mapper;
    private VoterRepository votes;
    @Override
    public AddVoteResponse addVote(AddVoteRequest request) {
        Candidate candidate = candidateService.findCandidateBy(request.getCandidateId());
        Voter voter = voterService.findVoterBy(request.getVoterId());
        Election election = adminService.findElectionBy(request.getElectionId());
        Vote vote = new Vote();
        vote.setVoter(voter);
//        vote.setCandidate(candidate);
        vote.setElection(election);
        VoterResponse voterResponse = mapper.map(voter,VoterResponse.class);
        AddVoteResponse addVoteResponse =  mapper.map(vote,AddVoteResponse.class);
        addVoteResponse.setVoterResponse(voterResponse);
        return addVoteResponse;
    }

    @Override
    public Long countElectionVote(Long electionId) {
        return votes.countVote(electionId);
    }

    @Override
    public Long countVoteForCandidate(CountCandidateVoteRequest request) {
        return votes.countVoteForCandidate(request.getElectionId(),candidateService.findCandidateBy(request.getCandidateId()).getCandidateId());
    }

    @Override
    public ShowElectionResultResponse showResult(Long electionId) {
        List<Candidate> candidates = adminService.getElectionCandidates(electionId);
        Map<String, Long> results = new HashMap<>();
        candidates.stream().forEach(candidate->{
            results.put(candidate.getFirstName(),votes.countVoteForCandidate(electionId,candidate.getCandidateId()));
        });

        return new ShowElectionResultResponse(electionId, results);
    }
}
>>>>>>> e92bd2cbacc5892350636f531552d8247f05b9fc
