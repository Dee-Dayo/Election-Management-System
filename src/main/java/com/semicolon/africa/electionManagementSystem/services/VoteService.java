package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountCandidateVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;

public interface VoteService {
    AddVoteResponse addVote(AddVoteRequest request);
    Long countElectionVote(Long voteId);
    Long countVoteForCandidate(CountCandidateVoteRequest request);

    ShowElectionResultResponse showResult(Long electionId);
}
