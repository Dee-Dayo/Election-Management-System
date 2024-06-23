package com.semicolon.africa.electionManagementSystem.dtos.requests;

import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import com.semicolon.africa.electionManagementSystem.models.Role;
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
    private Long electionId;
    private Category positionContested;
    private PartyAffiliation partyAffiliation;

}
