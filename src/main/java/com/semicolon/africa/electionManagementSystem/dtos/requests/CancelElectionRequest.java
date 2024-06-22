package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Data;

@Data
public class CancelElectionRequest {
    private Long adminId;
    private Long ElectionId;
    private String reason;
}
