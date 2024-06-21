package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountVoteForPartyRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;

public interface VoteService {
    AddVoteResponse addVote(AddVoteRequest request, AdminService adminService, VoterService voterService);

    Long countElectionVote(Long voteId);
    Long countVoteForParties(CountVoteForPartyRequest request);
    ShowElectionResultResponse showResult(Long electionId);
}
