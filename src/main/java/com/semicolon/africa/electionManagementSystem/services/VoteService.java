package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountVoteForPartyRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.VoteNotFoundException;

public interface VoteService {
    AddVoteResponse addVote(AddVoteRequest request, AdminService adminService, VoterService voterService);
    Long countElectionVote(Long electionID);
    Long countVoteForParties(CountVoteForPartyRequest request);
    ShowElectionResultResponse showResult(Long electionId) throws VoteNotFoundException;
}
