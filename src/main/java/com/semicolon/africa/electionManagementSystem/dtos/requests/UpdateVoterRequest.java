package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateVoterRequest {
    private Long voterId;
    private String firstName;
    private String lastName;
    private String password;
}
