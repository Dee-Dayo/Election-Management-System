package com.semicolon.africa.electionManagementSystem.services;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddVoteRequest {
    private Long candidateId;
    private Long voterId;
    private Long ElectionId;
}
