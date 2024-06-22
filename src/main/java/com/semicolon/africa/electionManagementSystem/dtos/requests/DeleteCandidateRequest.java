package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteCandidateRequest {
    private Long electionId;
    private Long adminId;
    private Long candidateId;
}
