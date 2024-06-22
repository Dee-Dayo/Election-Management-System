package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountVoteForPartyRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.VoterResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.VoteNotFoundException;
import com.semicolon.africa.electionManagementSystem.models.*;
import com.semicolon.africa.electionManagementSystem.repositories.VoteRepository;

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
    private VoterService voterService;
    private AdminService adminService;
    private ModelMapper mapper;
    private VoteRepository votes;
    @Override

    public AddVoteResponse addVote(AddVoteRequest request,@Autowired AdminService adminService,@Autowired VoterService voterService) {
        Voter voter = voterService.findVoterBy(request.getVoterId());
        Election election = adminService.findElectionBy(request.getElectionId());
        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setAffiliation(request.getAffiliation());
        vote.setElection(election);
        vote = votes.save(vote);
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
    public Long countVoteForParties(CountVoteForPartyRequest request) {
        return votes.countVoteByAffiliation(request.getElectionId(),request.getAffiliation());
    }

    @Override
    public ShowElectionResultResponse showResult(Long electionId) throws VoteNotFoundException {
       List<Vote> voteList = votes.findByElectionId(electionId);
        Map<PartyAffiliation,Long> results = new HashMap<> ();
        if(voteList.isEmpty()) throw new VoteNotFoundException("NO VOTE FOUND");
        for(PartyAffiliation affiliation: PartyAffiliation.values()){
            results.put(affiliation,votes.countVoteByAffiliation(electionId,affiliation));
        }
//        return new ShowElectionResultResponse(electionId, results);
        return null;
    }
}
