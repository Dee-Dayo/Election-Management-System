package com.semicolon.africa.electionManagementSystem.dtos.requests;

import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterCandidateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Category positionContested;
    private Long electionId;
    private PartyAffiliation partyAffiliation;

}
