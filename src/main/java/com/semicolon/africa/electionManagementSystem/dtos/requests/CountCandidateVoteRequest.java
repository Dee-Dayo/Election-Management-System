package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountCandidateVoteRequest {
    private Long candidateId;
    private Long ElectionId;
}
