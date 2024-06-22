package com.semicolon.africa.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddVoteResponse {
    private Long voteId;
    private VoterResponse voterResponse;
}
