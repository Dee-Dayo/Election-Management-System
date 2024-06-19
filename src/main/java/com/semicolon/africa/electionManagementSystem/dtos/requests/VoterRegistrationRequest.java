package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoterRegistrationRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String stateOfOrigin;
    private String email;
    private String password;
}
