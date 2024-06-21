package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountCandidateVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterResponse;
import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.models.Vote;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import com.semicolon.africa.electionManagementSystem.repositories.VoteRepository;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {
    @Autowired
    private final CandidateService candidateService;
    @Autowired
    private final VoterService voterService;
    @Autowired
    private final AdminService adminService;
    @Autowired
    private final ModelMapper mapper;
    @Autowired
    private final VoteRepository votes;

    @Override
    public AddVoteResponse addVote(AddVoteRequest request) {
        Candidate candidate = candidateService.findCandidateBy(request.getCandidateId());
        Voter voter = voterService.findVoterBy(request.getVoterId());
        Election election = adminService.getElection(request.getElectionId());
        Vote vote = new Vote();
        vote.setVoter(voter);
        // vote.setCandidate(candidate);
        vote.setElection(election);
        VoterResponse voterResponse = mapper.map(voter, VoterResponse.class);
        AddVoteResponse addVoteResponse = mapper.map(vote, AddVoteResponse.class);
        addVoteResponse.setVoterResponse(voterResponse);
        return addVoteResponse;
    }

    @Override
    public Long countElectionVote(Long electionId) {
        return 0L;
    }

    @Override
    public Long countVoteForCandidate(CountCandidateVoteRequest request) {
        return null;
//        return votes.countVoteForCandidate(request.getElectionId(), candidateService.findCandidateBy(request.getCandidateId()).getCandidateId());
    }

    @Override
    public ShowElectionResultResponse showResult(Long electionId) {
        List<Candidate> candidates = adminService.getElectionCandidates(electionId);
        Map<String, Long> results = new HashMap<>();
//        candidates.forEach(candidate -> {
//            results.put(candidate.getFirstName(), votes.countVoteForCandidate(electionId, candidate.getCandidateId()));
//        });

        return new ShowElectionResultResponse(electionId, results);
    }
}
