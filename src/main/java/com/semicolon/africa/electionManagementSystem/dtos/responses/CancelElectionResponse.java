package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.Election;
import lombok.Data;

@Data
public class CancelElectionResponse {
//    private Long electionId;
    private Long cancellerId;
    private Election election;
    private String firstName;
    private String lastName;
    private String message;
    private String reason;
}
