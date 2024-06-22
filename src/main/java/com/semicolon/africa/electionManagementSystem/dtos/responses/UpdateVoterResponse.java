package com.semicolon.africa.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateVoterResponse {
    private Long voterId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String stateOfOrigin;
    private String email;
    private String message;
}
