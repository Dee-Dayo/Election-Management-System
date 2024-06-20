package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddVoteRequest {
    private Long candidateId;
    private Long voterId;
    private Long ElectionId;
}
