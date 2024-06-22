package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelElectionRequest {
    private Long adminId;
    private Long ElectionId;
    private String reason;
}
